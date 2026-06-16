import { useCallback, useEffect, useState } from 'react';
import type { BookDetails } from '../../api/types/book';
import bookApi from "../../api/bookApi";

const useBookDetails = (id?: string) => {
    const [bookDetails, setBookDetails] = useState<BookDetails | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [error, setError] = useState<Error | null>(null);

    const fetch = useCallback(async () => {
        if (!id) {
            return;
        }

        setLoading(true);

        try {
            const response = await bookApi.findWithDetailsById(id);
            setBookDetails(response.data);
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

    return { bookDetails, loading, error };
};

export default useBookDetails;
