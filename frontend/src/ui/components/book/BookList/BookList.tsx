// import { Box, List, ListItem, ListItemText, Divider, Button, Stack } from '@mui/material';
// import InfoIcon from '@mui/icons-material/Info';
// import EditIcon from '@mui/icons-material/Edit';
// import DeleteIcon from '@mui/icons-material/Delete';
// import { useNavigate } from 'react-router';
// import type { Book } from "../../../../api/types/book";
// import React from "react";
//
// interface BookListProps {
//     books: Book[];
// }
//
// const BookList = ({ books }: BookListProps) => {
//     const navigate = useNavigate();
//
//     return (
//         <Box>
//             <List>
//                 {books.map((book) => (
//                     <React.Fragment key={book.id}>
//                         <ListItem
//                             sx={{
//                                 display: 'flex',
//                                 justifyContent: 'space-between',
//                                 alignItems: 'center',
//                                 padding: 2,
//                                 borderBottom: '1px solid #ddd'
//                             }}
//                         >
//                             <ListItemText
//                                 primary={book.name}
//                                 secondary={`${book.category} - ${book.availableCopies} copies available`}
//                                 sx={{ width: '70%' }}
//                             />
//
//                             <Stack direction="row" spacing={1}>
//                                 <Button
//                                     size="small"
//                                     startIcon={<InfoIcon />}
//                                     onClick={() => navigate(`/books/${book.id}/details`)}
//                                 >
//                                     Info
//                                 </Button>
//                                 <Button
//                                     size="small"
//                                     startIcon={<EditIcon />}
//                                     color="warning"
//                                 >
//                                     Edit
//                                 </Button>
//                                 <Button
//                                     size="small"
//                                     startIcon={<DeleteIcon />}
//                                     color="error"
//                                 >
//                                     Delete
//                                 </Button>
//                             </Stack>
//                         </ListItem>
//                         <Divider />
//                     </React.Fragment>
//                 ))}
//             </List>
//         </Box>
//     );
// };
//
// export default BookList;
import {
    Alert,
    Box,
    Button,
    Divider,
    List,
    ListItem,
    ListItemText,
    Snackbar,
    Stack
} from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router';
import type { Book, BookFormData } from "../../../../api/types/book";
import React, { useState } from "react";
import useAuth from "../../../../hooks/useAuth";
import EditBookDialog from "../dialog/EditBookDialog/EditBookDialog";
import DeleteBookDialog from "../dialog/DeleteBookDialog/DeleteBookDialog";

interface BookListProps {
    books: Book[];
    onEdit: (id: number, data: BookFormData) => Promise<void>;
    onDelete: (id: number) => Promise<void>;
}

const BookList = ({ books, onEdit, onDelete }: BookListProps) => {
    const navigate = useNavigate();

    const { user } = useAuth();
    const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

    const [selectedBook, setSelectedBook] = useState<Book | null>(null);

    const [editBookDialogOpen, setEditBookDialogOpen] = useState(false);
    const [deleteBookDialogOpen, setDeleteBookDialogOpen] = useState(false);

    const [snackbar, setSnackbar] = useState<{ open: boolean; message: string; }>({
        open: false,
        message: ''
    });

    const handleEdit = async (id: number, data: BookFormData) => {
        try {
            await onEdit(id, data);
            setEditBookDialogOpen(false);
        } catch (err) {
            setSnackbar({
                open: true,
                message: err instanceof Error ? err.message : 'Failed to edit book.'
            });
        }
    };

    const handleDelete = async (id: number) => {
        try {
            await onDelete(id);
            setDeleteBookDialogOpen(false);
        } catch (err) {
            setSnackbar({
                open: true,
                message: err instanceof Error ? err.message : 'Failed to delete book.'
            });
        }
    };

    return (
        <>
            <Box>
                <List>
                    {books.map((book) => (
                        <React.Fragment key={book.id}>
                            <ListItem
                                sx={{
                                    display: 'flex',
                                    justifyContent: 'space-between',
                                    alignItems: 'center',
                                    padding: 2
                                }}
                            >
                                <ListItemText
                                    primary={book.name}
                                    secondary={`${book.category} - ${book.availableCopies} copies available`}
                                    sx={{ width: '70%' }}
                                />

                                <Stack direction="row" spacing={1}>
                                    <Button
                                        size="small"
                                        startIcon={<InfoIcon />}
                                        onClick={() =>
                                            navigate(`/books/${book.id}/details`)
                                        }
                                    >
                                        Info
                                    </Button>

                                    {isAdmin && (
                                        <Button
                                            size="small"
                                            startIcon={<EditIcon />}
                                            color="warning"
                                            onClick={() => {
                                                setSelectedBook(book);
                                                setEditBookDialogOpen(true);
                                            }}
                                        >
                                            Edit
                                        </Button>
                                    )}

                                    {isAdmin && (
                                        <Button
                                            size="small"
                                            startIcon={<DeleteIcon />}
                                            color="error"
                                            onClick={() => {
                                                setSelectedBook(book);
                                                setDeleteBookDialogOpen(true);
                                            }}
                                        >
                                            Delete
                                        </Button>
                                    )}
                                </Stack>
                            </ListItem>

                            <Divider />
                        </React.Fragment>
                    ))}
                </List>
            </Box>

            <Snackbar
                open={snackbar.open}
                autoHideDuration={3000}
                onClose={() => setSnackbar((prev) => ({...prev, open: false}))}
                anchorOrigin={{ vertical: 'bottom', horizontal: 'right'}}
            >
                <Alert
                    severity="error"
                    onClose={() =>
                        setSnackbar((prev) => ({...prev, open: false}))}
                >
                    {snackbar.message}
                </Alert>
            </Snackbar>

            {selectedBook && (
                <>
                    <EditBookDialog
                        book={selectedBook}
                        open={editBookDialogOpen}
                        onClose={() => setEditBookDialogOpen(false)}
                        onEdit={handleEdit}
                    />
                    <DeleteBookDialog
                        book={selectedBook}
                        open={deleteBookDialogOpen}
                        onClose={() => setDeleteBookDialogOpen(false)}
                        onDelete={handleDelete}
                    />
                </>
            )}
        </>
    );
};

export default BookList;