// import './BooksPage.css';
// import React, { useEffect, useState } from 'react';
// import {
//     Alert,
//     Box,
//     Button,
//     CircularProgress,
//     Snackbar,
//     ToggleButton,
//     ToggleButtonGroup
// } from '@mui/material';
// import useBooks from '../../../../hooks/books/useBooks';
// import useAuth from '../../../../hooks/useAuth';
// import type { BookFormData } from '../../../../api/types/book';
// import BookGrid from '../../../components/book/BookGrid/BookGrid';
// import BookList from '../../../components/book/BookList/BookList';
// import AddBookDialog from '../../../components/book/dialog/AddBookDialog/AddBookDialog';
//
// const BooksPage = () => {
//     const { user } = useAuth();
//     const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;
//
//     const {books, loading, onAdd, onEdit, onDelete} = useBooks();
//     const [addBookDialogOpen, setAddBookDialogOpen] = useState<boolean>(false);
//
//     const [snackbar, setSnackbar] = useState<{ open: boolean; message: string; }>({
//         open: false,
//         message: ''
//     });
//
//     const storageKey = `bookViewMode_${user?.username}`;
//
//     const [viewMode, setViewMode] =
//         useState<'card' | 'list'>(() => {
//             const savedView = localStorage.getItem(storageKey);
//             if (savedView === 'card' || savedView === 'list') {
//                 return savedView;
//             }
//             return 'card';
//         });
//
//     useEffect(() => {
//         localStorage.setItem(storageKey, viewMode);
//     }, [viewMode, storageKey]);
//
//
//     const handleAdd = async (data: BookFormData) => {
//         try {
//             await onAdd(data);
//         } catch (err) {
//             setSnackbar({
//                 open: true,
//                 message: err instanceof Error ? err.message : 'Failed to add book.'
//             });
//         }
//     };
//
//     const handleViewChange = (_event: React.MouseEvent<HTMLElement>, newView: 'card' | 'list') => {
//         if (newView !== null) {
//             setViewMode(newView);
//         }
//     };
//
//     return (
//         <Box className='books-box'>
//             <Box sx={{ marginBottom: 2 }}>
//                 <ToggleButtonGroup
//                     value={viewMode}
//                     exclusive
//                     onChange={handleViewChange}
//                     aria-label='view mode'
//                 >
//                     <ToggleButton
//                         value='card'
//                         aria-label='card view'
//                     >
//                         Card View
//                     </ToggleButton>
//
//                     <ToggleButton
//                         value='list'
//                         aria-label='list view'
//                     >
//                         List View
//                     </ToggleButton>
//                 </ToggleButtonGroup>
//
//             </Box>
//
//             {loading && (
//                 <Box className='progress-box'>
//                     <CircularProgress />
//                 </Box>
//             )}
//
//             {!loading && (
//                 <>
//                     {isAdmin && (
//                         <Box
//                             sx={{
//                                 display: 'flex',
//                                 justifyContent: 'flex-end',
//                                 mb: 2
//                             }}
//                         >
//                             <Button
//                                 variant='contained'
//                                 color='primary'
//                                 onClick={() =>
//                                     setAddBookDialogOpen(true)
//                                 }
//                             >
//                                 Add Book
//                             </Button>
//
//                         </Box>
//                     )}
//                     {viewMode === 'card' ? (
//
//                         <BookGrid
//                             books={books}
//                             onEdit={onEdit}
//                             onDelete={onDelete}
//                         />
//
//                     ) : (
//
//                         <BookList
//                             books={books}
//                             onEdit={onEdit}
//                             onDelete={onDelete}
//                         />
//
//                     )}
//
//                     <Snackbar
//                         open={snackbar.open}
//                         autoHideDuration={3000}
//                         onClose={() =>
//                             setSnackbar((prev) => ({
//                                 ...prev,
//                                 open: false
//                             }))
//                         }
//                         anchorOrigin={{
//                             vertical: 'bottom',
//                             horizontal: 'right'
//                         }}
//                     >
//
//                         <Alert
//                             severity='error'
//                             onClose={() =>
//                                 setSnackbar((prev) => ({
//                                     ...prev,
//                                     open: false
//                                 }))
//                             }
//                         >
//                             {snackbar.message}
//                         </Alert>
//
//                     </Snackbar>
//
//                     <AddBookDialog
//                         open={addBookDialogOpen}
//                         onClose={() =>
//                             setAddBookDialogOpen(false)
//                         }
//                         onAdd={handleAdd}
//                     />
//
//                 </>
//
//             )}
//
//         </Box>
//     );
// };
//
// export default BooksPage;



import './BooksPage.css';
import React, { useState } from 'react';
import {
    Alert,
    Box,
    Button,
    CircularProgress,
    Snackbar,
    ToggleButton,
    ToggleButtonGroup
} from '@mui/material';
import useBooks from '../../../../hooks/books/useBooks';
import useAuth from '../../../../hooks/useAuth';
import useBookViewPreference from "../../../../hooks/useBookViewPreference";
import type { BookFormData } from '../../../../api/types/book';
import BookGrid from '../../../components/book/BookGrid/BookGrid';
import BookList from '../../../components/book/BookList/BookList';
import AddBookDialog from '../../../components/book/dialog/AddBookDialog/AddBookDialog';

const BooksPage = () => {
    const { user } = useAuth();
    const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

    const {books, loading, onAdd, onEdit, onDelete} = useBooks();
    const {viewMode, setViewMode, loading: preferenceLoading} = useBookViewPreference();
    const [addBookDialogOpen, setAddBookDialogOpen] = useState<boolean>(false);
    const [snackbar, setSnackbar] = useState<{ open: boolean; message: string; }>({
        open: false,
        message: ''
    });

    const handleAdd = async (data: BookFormData) => {
        try {
            await onAdd(data);
        } catch (err) {
            setSnackbar({
                open: true,
                message: err instanceof Error ? err.message : 'Failed to add book.'
            });
        }
    };

    const handleViewChange = async (
        _event: React.MouseEvent<HTMLElement>,
        newView: 'card' | 'list'
    ) => {

        if (newView !== null) {
            try {
                await setViewMode(newView);
            } catch (err) {
                setSnackbar({
                    open: true,
                    message: err instanceof Error ? err.message : 'Failed to update preference.'
                });
            }
        }
    };

    if (loading || preferenceLoading) {
        return (
            <Box className='progress-box'>
                <CircularProgress />
            </Box>
        );
    }


    return (
        <Box className='books-box'>
            <Box sx={{display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: 2}}>
                <ToggleButtonGroup
                    value={viewMode}
                    exclusive
                    onChange={handleViewChange}
                    aria-label='view mode'
                >
                    <ToggleButton
                        value='card'
                        aria-label='card view'
                    >
                        Card View
                    </ToggleButton>
                    <ToggleButton
                        value='list'
                        aria-label='list view'
                    >
                        List View
                    </ToggleButton>
                </ToggleButtonGroup>

                {isAdmin && (

                    <Button
                        variant='contained'
                        color='primary'
                        onClick={() => setAddBookDialogOpen(true)}
                    >
                        Add Book
                    </Button>
                )}
            </Box>

            {viewMode === 'card' ? (
                <BookGrid
                    books={books}
                    onEdit={onEdit}
                    onDelete={onDelete}
                />
            ) : (
                <BookList
                    books={books}
                    onEdit={onEdit}
                    onDelete={onDelete}
                />
            )}

            <Snackbar
                open={snackbar.open}
                autoHideDuration={3000}
                onClose={() =>
                    setSnackbar((prev) => ({...prev, open: false}))
                }
                anchorOrigin={{
                    vertical: 'bottom',
                    horizontal: 'right'
                }}
            >
                <Alert
                    severity='error'
                    onClose={() =>
                        setSnackbar((prev) => ({...prev, open: false}))
                    }
                >
                    {snackbar.message}
                </Alert>
            </Snackbar>

            <AddBookDialog
                open={addBookDialogOpen}
                onClose={() => setAddBookDialogOpen(false)}
                onAdd={handleAdd}
            />
        </Box>
    );
};

export default BooksPage;