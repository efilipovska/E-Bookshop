import './AuthorsPage.css';
import {Alert, Box, Button, CircularProgress, Snackbar} from '@mui/material';
import useAuthors from "../../../../hooks/authors/useAuthors";
import AuthorGrid from "../../../components/author/AuthorGrid";
import useAuth from "../../../../hooks/useAuth";
import {useState} from "react";
import type {AuthorFormData} from "../../../../api/types/author";
import AddAuthorDialog from "../../../components/author/dialog/AddAuthorDialog";

const AuthorsPage = () => {
    const { user } = useAuth();
    const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

    const { authors, loading, onAdd, onEdit, onDelete } = useAuthors();
    const [addAuthorDialogOpen, setAddAuthorDialogOpen] = useState<boolean>(false);

    const [snackbar, setSnackbar] = useState<{ open: boolean; message: string }>({
        open: false,
        message: ''
    });

    const handleAdd = async (data: AuthorFormData) => {
        try {
            await onAdd(data);
        } catch (err) {
            setSnackbar({
                open: true,
                message: err instanceof Error ? err.message : 'Failed to add author.'
            });
        }
    };

    return (
        <Box className='authors-box'>
            {loading && (
                <Box className='progress-box'>
                    <CircularProgress/>
                </Box>
            )}
            {!loading &&
                <>
                    {isAdmin && (
                        <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
                            <Button variant='contained' color='primary' onClick={() => setAddAuthorDialogOpen(true)}>
                                Add Author
                            </Button>
                        </Box>
                    )}

                    <AuthorGrid authors={authors} onEdit={onEdit} onDelete={onDelete}/>

                    <Snackbar
                        open={snackbar.open}
                        autoHideDuration={3000}
                        onClose={() => setSnackbar((prev) => ({ ...prev, open: false }))}
                        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
                    >
                        <Alert
                            severity='error'
                            onClose={() => setSnackbar((prev) => ({ ...prev, open: false }))}>
                            {snackbar.message}
                        </Alert>
                    </Snackbar>

                    <AddAuthorDialog
                        open={addAuthorDialogOpen}
                        onClose={() => setAddAuthorDialogOpen(false)}
                        onAdd={handleAdd}
                    />
                </>
            }
        </Box>
    );
};

export default AuthorsPage;
