import {Alert, Box, Button, Card, CardActions, CardContent, Snackbar, Typography} from '@mui/material';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router';
import type {Country, CountryFormData} from "../../../api/types/country";
import useAuth from "../../../hooks/useAuth";
import {useState} from "react";
import EditCountryDialog from "./dialog/EditCountryDialog";
import DeleteCountryDialog from "./dialog/DeleteCountryDialog";

interface CountryCardProps {
    country: Country;
    onEdit: (id: number, data: CountryFormData) => Promise<void>;
    onDelete: (id: number) => Promise<void>;
}

const CountryCard = ({ country, onEdit, onDelete }: CountryCardProps) => {
    const navigate = useNavigate();

    const { user } = useAuth();
    const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

    const [snackbar, setSnackbar] = useState<{ open: boolean; message: string }>({
        open: false,
        message: ''
    });

    const [editCountryDialogOpen, setEditCountryDialogOpen] = useState<boolean>(false);
    const [deleteCountryDialogOpen, setDeleteCountryDialogOpen] = useState<boolean>(false);

    const handleEdit = async (id: number, data: CountryFormData) => {
        try {
            await onEdit(id, data);
        } catch (err) {
            setSnackbar({
                open: true,
                message: err instanceof Error ? err.message : 'Failed to edit country.'
            });
        }
    };

    const handleDelete = async (id: number) => {
        try {
            await onDelete(id);
        } catch (err) {
            setSnackbar({
                open: true,
                message: err instanceof Error ? err.message : 'Failed to delete country.'
            });
        }
    };


    return (
        <>
            <Card sx={{ maxWidth: 300, height: '100%', display: 'flex', flexDirection: 'column' }}>
                <CardContent sx={{ flexGrow: 1, display: 'flex', flexDirection: 'column' }}>
                    <Typography variant='h5'>{country.name}</Typography>
                </CardContent>
                <CardActions sx={{ justifyContent: 'space-between' }}>
                    <Button
                        startIcon={<InfoIcon/>}
                        onClick={() => navigate(`/countries/${country.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        {isAdmin && (
                            <Button
                                startIcon={<EditIcon/>}
                                color='warning'
                                onClick={() => setEditCountryDialogOpen(true)}
                            >
                                Edit
                            </Button>
                        )}
                        {isAdmin && (
                            <Button
                                startIcon={<DeleteIcon/>}
                                color='error'
                                onClick={() => setDeleteCountryDialogOpen(true)}
                            >
                                Delete
                            </Button>
                        )}
                    </Box>
                </CardActions>
            </Card>

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

            <EditCountryDialog
                country={country}
                open={editCountryDialogOpen}
                onClose={() => setEditCountryDialogOpen(false)}
                onEdit={handleEdit}
            />
            <DeleteCountryDialog
                country={country}
                open={deleteCountryDialogOpen}
                onClose={() => setDeleteCountryDialogOpen(false)}
                onDelete={handleDelete}
            />
        </>
    );
};

export default CountryCard;
