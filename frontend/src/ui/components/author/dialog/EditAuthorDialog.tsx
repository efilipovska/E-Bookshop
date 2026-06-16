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
import type {Author, AuthorFormData} from "../../../../api/types/author";
import useCountries from "../../../../hooks/countries/useCountries";

interface FormData {
    name: string;
    surname: string;
    countryId: string;
}

interface EditAuthorDialogProps {
    author: Author
    open: boolean
    onClose: () => void
    onEdit: (id: number, data: AuthorFormData) => Promise<void>
}

const EditAuthorDialog = ({ author, open, onClose, onEdit }: EditAuthorDialogProps) => {
    const { countries } = useCountries()

    const [formData, setFormData] =
        useState<FormData>({
            name: author.name,
            surname: author.surname,
            countryId: author.countryId.toString(),
        })

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
            countryId: Number(formData.countryId),
        }
        await onEdit(author.id, payload)
        onClose()
    }

    return (
        <Dialog open={open} onClose={onClose} fullWidth maxWidth='sm'>
            <DialogTitle>Edit Book</DialogTitle>
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
                    <InputLabel>Author</InputLabel>
                    <Select
                        label='Coutry'
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
                    Edit
                </Button>
            </DialogActions>
        </Dialog>
    )
}

export default EditAuthorDialog