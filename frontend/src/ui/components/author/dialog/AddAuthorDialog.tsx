import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField,
    type SelectChangeEvent
} from '@mui/material'

import { useState } from 'react'
import * as React from 'react'
import type {AuthorFormData} from "../../../../api/types/author";
import useCountries from "../../../../hooks/countries/useCountries";

interface FormData {
    name: string;
    surname: string;
    countryId: string;
}

const initialFormData: FormData = {
    name: '',
    surname: '',
    countryId: '',
}

interface AddAuthorDialogProps {
    open: boolean
    onClose: () => void
    onAdd: (data: AuthorFormData) => Promise<void>
}

const AddAuthorDialog = ({ open, onClose, onAdd }: AddAuthorDialogProps) => {
    const { countries } = useCountries()
    const [formData, setFormData] = useState<FormData>(initialFormData)

    const handleChange = (
        event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement> | SelectChangeEvent
    ) => {
        const { name, value } = event.target
        setFormData((prev) => ({ ...prev, [name]: value }))
    }

    const handleSubmit = async () => {
        const payload: AuthorFormData = {
            name: formData.name.trim(),
            surname: formData.surname.trim(),
            countryId: Number(formData.countryId)
        }
        await onAdd(payload)
        setFormData(initialFormData)
        onClose()
    }

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth='sm'>
            <DialogTitle>Add Book</DialogTitle>
            <DialogContent>
                <TextField
                    margin='dense'
                    label='Name'
                    name='name'
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin='dense'
                    label='Surname'
                    name='surname'
                    value={formData.surname}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl margin='dense' fullWidth>
                    <InputLabel>Country</InputLabel>
                    <Select
                        label='Country'
                        name='countryId'
                        value={formData.countryId}
                        onChange={handleChange}
                    >
                        {countries.map((country) => (
                            <MenuItem
                                key={country.id}
                                value={country.id}
                            >
                                {country.name}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>
                    Cancel
                </Button>
                <Button
                    onClick={handleSubmit}
                    variant='contained'
                >
                    Add
                </Button>
            </DialogActions>
        </Dialog>
    )
}

export default AddAuthorDialog