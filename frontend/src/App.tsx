import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router';
import Layout from "./ui/components/layout/Layout/Layout";
import HomePage from "./ui/pages/HomePage/HomePage";
import BooksPage from "./ui/pages/books/BooksPage/BooksPage";
import BookDetailsPage from "./ui/pages/books/BookDetailsPage/BookDetailsPage";
import AuthorsPage from "./ui/pages/authors/AuthorsPage/AuthorsPage";
import AuthorDetailsPage from "./ui/pages/authors/AuthorDetailsPage/AuthorDetailsPage";
import CountriesPage from "./ui/pages/countries/CountriesPage/CountriesPage";
import CountryDetailsPage from "./ui/pages/countries/CountryDetailsPage/CountryDetailsPage";
import ProtectedRoute from "./ui/components/routing/ProtectedRoute/ProtectedRoute";
import LoginPage from "./ui/pages/auth/LoginPage/LoginPage";
import RegisterPage from "./ui/pages/auth/RegisterPage/RegisterPage";

function App() {
  return (
      <BrowserRouter>
        <Routes>
            <Route path='/register' element={<RegisterPage/>}/>
            <Route path='/login' element={<LoginPage/>}/>

            <Route path="/" element={<Layout />}>
                <Route index element={<HomePage />} />
                <Route element={<ProtectedRoute/>}>
                    <Route path="books" element={<BooksPage />} />
                    <Route path="books/:id/details" element={<BookDetailsPage />} />
                    <Route path="authors" element={<AuthorsPage />} />
                    <Route path="authors/:id/details" element={<AuthorDetailsPage />} />
                    <Route path="countries" element={<CountriesPage />} />
                    <Route path="countries/:id" element={<CountryDetailsPage />} />
                </Route>
            </Route>
        </Routes>
      </BrowserRouter>
  );
}

export default App
