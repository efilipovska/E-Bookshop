import { useEffect, useState } from 'react';
import {getBookViewPreference, updateBookViewPreference} from '../api/preferenceApi';

const useBookViewPreference = () => {

    const [viewMode, setViewModeState] =
        useState<'card' | 'list'>('card');

    const [loading, setLoading] =
        useState(true);

    useEffect(() => {

        const fetchPreference = async () => {

            try {

                const response =
                    await getBookViewPreference();

                if (response === 'CARD') {
                    setViewModeState('card');
                } else {
                    setViewModeState('list');
                }

            } catch (error) {
                console.error(error);
            } finally {
                setLoading(false);
            }
        };

        fetchPreference();

    }, []);

    const setViewMode = async (
        mode: 'card' | 'list'
    ) => {

        setViewModeState(mode);

        await updateBookViewPreference(mode);
    };

    return {
        viewMode,
        setViewMode,
        loading
    };
};

export default useBookViewPreference;