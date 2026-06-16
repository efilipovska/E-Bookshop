import { useCallback, useEffect, useState } from 'react';
import type {CountryDetails} from "../../api/types/country";
import countryApi from "../../api/countryApi";

const useCountryDetails = (id?: string) => {
    const [countryDetails, setCountryDetails] = useState<CountryDetails | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<Error | null>(null);

    const fetch = useCallback(async () => {
        if (!id) {
            return;
        }

        setLoading(true);

        try {
            const response = await countryApi.findWithDetailsById(id);
            setCountryDetails(response.data);
            setError(null);
        } catch (err) {
            setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
        } finally {
            setLoading(false);
        }
    }, [id]);

    useEffect(() => {
        // eslint-disable-next-line react-hooks/set-state-in-effect
        void fetch();
    }, [fetch]);

    return { countryDetails: countryDetails, loading, error };
};

export default useCountryDetails;
