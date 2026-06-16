import type { Author } from './author';

export const BookCategory = {
    NOVEL: 'NOVEL',
    THRILLER: 'THRILLER',
    HISTORY: 'HISTORY',
    FANTASY: 'FANTASY',
    BIOGRAPHY: 'BIOGRAPHY',
    CLASSICS: 'CLASSICS',
    DRAMA: 'DRAMA',
} as const;
export type BookCategory = (typeof BookCategory)[keyof typeof BookCategory];

export const BookState = {
    GOOD: 'GOOD',
    BAD: 'BAD',
} as const;
export type BookState = (typeof BookState)[keyof typeof BookState];


export interface Book {
    id: number,
    name: string;
    category: BookCategory;
    authorId: number;
    state: BookState;
    availableCopies: number;
    datePublished: Date;
    deleted: false;
}

export interface BookDetails {
    id: number,
    name: string;
    category: BookCategory;
    author: Author;
    state: BookState;
    availableCopies: number;
    datePublished: Date;
}

export interface BookFormData {
    name: string;
    category: BookCategory;
    authorId: number;
    state: BookState;
    availableCopies: number;
    datePublished: Date;
    deleted: boolean;
}
