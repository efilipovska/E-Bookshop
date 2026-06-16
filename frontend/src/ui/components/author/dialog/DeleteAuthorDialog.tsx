import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogContentText,
    DialogTitle
} from '@mui/material'
import type {Author} from "../../../../api/types/author";

interface DeleteAuthorDialogProps {
    author: Author
    open: boolean
    onClose: () => void
    onDelete: (id: number) => Promise<void>
}

const DeleteAuthorDialog = ({ author, open, onClose, onDelete }: DeleteAuthorDialogProps) => {

    const handleSubmit = async () => {
        await onDelete(author.id)
        onClose()
    }

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Delete Book</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Are you sure you want to delete
                    <strong> {author.name} </strong>?
                </DialogContentText>
                <DialogActions>
                    <Button onClick={onClose}>
                        Cancel
                    </Button>
                    <Button
                        onClick={handleSubmit}
                        color='error'
                        variant='contained'
                    >
                        Delete
                    </Button>
                </DialogActions>
            </DialogContent>
        </Dialog>
    )
}

export default DeleteAuthorDialog