import './CountriesPage.css';
import {Alert, Box, Button, CircularProgress, Snackbar} from '@mui/material';
import useCountries from "../../../../hooks/countries/useCountries";
import CountryGrid from "../../../components/country/CountryGrid";
import useAuth from "../../../../hooks/useAuth";
import {useState} from "react";
import type {CountryFormData} from "../../../../api/types/country";
import AddCountryDialog from "../../../components/country/dialog/AddCountryDialog";

const CountriesPage = () => {
    const { user } = useAuth();
    const isAdmin = user?.roles.includes('ROLE_ADMINISTRATOR') ?? false;

    const { countries, loading, onAdd, onEdit, onDelete } = useCountries();
    const [addCountryDialogOpen, setAddCountryDialogOpen] = useState<boolean>(false);

    const [snackbar, setSnackbar] = useState<{ open: boolean; message: string }>({
        open: false,
        message: ''
    });

    const handleAdd = async (data: CountryFormData) => {
        try {
            await onAdd(data);
        } catch (err) {
            setSnackbar({
                open: true,
                message: err instanceof Error ? err.message : 'Failed to add country.'
            });
        }
    };

    return (
        <Box className='countries-box'>
            {loading && (
                <Box className='progress-box'>
                    <CircularProgress/>
                </Box>
            )}
            {!loading &&
                <>
                    {isAdmin && (
                        <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
                            <Button variant='contained' color='primary' onClick={() => setAddCountryDialogOpen(true)}>
                                Add Country
                            </Button>
                        </Box>
                    )}

                    <CountryGrid countries={countries} onEdit={onEdit} onDelete={onDelete}/>

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

                    <AddCountryDialog
                        open={addCountryDialogOpen}
                        onClose={() => setAddCountryDialogOpen(false)}
                        onAdd={handleAdd}
                    />
                </>
            }
        </Box>
    );
};

export default CountriesPage;
