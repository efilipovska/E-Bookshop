import axiosInstance from "../axios/axios";

export const getBookViewPreference = async () => {
    const response = await axiosInstance.get('/user/book-view');
    return response.data;
};

export const updateBookViewPreference = async (viewMode: 'card' | 'list') => {
    await axiosInstance.put('/user/book-view',
        {
            viewMode: viewMode.toUpperCase()
        }
    );
};