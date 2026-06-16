import {BookCategory, BookState} from '../../../../../api/types/book'
import type {Book, BookFormData} from '../../../../../api/types/book'
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
import useAuthors from "../../../../../hooks/authors/useAuthors";

interface FormData {
    name: string;
    category: string;
    authorId: string;
    state: string;
    availableCopies: string;
    datePublished: string;
    deleted: boolean
}

interface EditBookDialogProps {
    book: Book
    open: boolean
    onClose: () => void
    onEdit: (id: number, data: BookFormData) => Promise<void>
}

const EditBookDialog = ({ book, open, onClose, onEdit }: EditBookDialogProps) => {
    const { authors } = useAuthors()

    const [formData, setFormData] =
        useState<FormData>({
            name: book.name,
            category: book.category,
            authorId: book.authorId.toString(),
            state: book.state.toString(),
            availableCopies: book.availableCopies.toString(),
            datePublished: book.datePublished.toString(),
            deleted: book.deleted
        })

    const handleChange = (
        event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement> | SelectChangeEvent
    ) => {
        const { name, value } = event.target
        setFormData((prev) => ({ ...prev, [name]: value }))
    }

    const handleSubmit = async () => {
        const payload: BookFormData = {
            name: formData.name.trim(),
            category: formData.category.trim() as BookCategory,
            authorId: Number(formData.authorId),
            state: formData.state.trim() as BookState,
            availableCopies: Number(formData.availableCopies),
            datePublished: new Date(formData.datePublished),
            deleted : book.deleted
        }
        await onEdit(book.id, payload)
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
                <FormControl margin='dense' fullWidth>
                    <InputLabel>Category</InputLabel>

                    <Select
                        label='Category'
                        name='category'
                        value={formData.category}
                        onChange={handleChange}
                    >
                        {Object.values(BookCategory).map((category) => (
                            <MenuItem
                                key={category}
                                value={category}
                            >
                                {category}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl margin='dense' fullWidth>
                    <InputLabel>State</InputLabel>

                    <Select
                        label='State'
                        name='state'
                        value={formData.state}
                        onChange={handleChange}
                    >
                        {Object.values(BookState).map((state) => (
                            <MenuItem
                                key={state}
                                value={state}
                            >
                                {state}
                            </MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <TextField
                    margin='dense'
                    label='Available Copies'
                    name='availableCopies'
                    value={formData.availableCopies}
                    onChange={handleChange}
                    type='number'
                    fullWidth
                />
                <TextField
                    margin='dense'
                    label='Date Published'
                    name='datePublished'
                    value={formData.datePublished}
                    onChange={handleChange}
                    type='date'
                    fullWidth
                />
                <FormControl margin='dense' fullWidth>
                    <InputLabel>Author</InputLabel>
                    <Select
                        label='Author'
                        name='authorId'
                        value={formData.authorId}
                        onChange={handleChange}
                    >
                        {authors.map((author) => (
                            <MenuItem
                                key={author.id}
                                value={author.id}
                            >
                                {author.name} {author.surname}
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

export default EditBookDialog