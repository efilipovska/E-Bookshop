import { useEffect, useState, useCallback } from 'react';
import type {Book, BookFormData} from "../../api/types/book";
import bookApi from "../../api/bookApi";

const useBooks = () => {
    const [books, setBooks] = useState<Book[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<Error | null>(null);

    const fetch = useCallback(async () => {
        setLoading(true);

        try {
            const response = await bookApi.findAll();
            setBooks(response.data);
            setError(null);
        } catch (err) {
            setError(err instanceof Error ? err : new Error('An unknown error occurred.'));
        } finally {
            setLoading(false);
        }
    }, []);

    const onAdd = useCallback(async (data: BookFormData) => {
        await bookApi.add(data);
        await fetch();
    }, [fetch]);

    const onEdit = useCallback(async (id: number, data: BookFormData) => {
        await bookApi.edit(id.toString(), data);
        await fetch();
    }, [fetch]);

    const onDelete = useCallback(async (id: number) => {
        await bookApi.delete(id.toString());
        await fetch();
    }, [fetch]);

    useEffect(() => {
        // eslint-disable-next-line react-hooks/set-state-in-effect
        void fetch();
    }, [fetch]);

    return { books, loading, error, fetch, onAdd, onEdit, onDelete };
};

export default useBooks;
