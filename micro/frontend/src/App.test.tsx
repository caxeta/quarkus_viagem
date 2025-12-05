import { render, screen } from '@testing-library/react';
import App from './App';
import { describe, it, expect, vi } from 'vitest';
import React from 'react';

// Mock the API calls
vi.mock('./api', () => ({
    getTravelOrders: vi.fn(() => Promise.resolve({ data: [] })),
    getHotels: vi.fn(() => Promise.resolve({ data: [] })),
    getFlights: vi.fn(() => Promise.resolve({ data: [] })),
}));

describe('App', () => {
    it('renders navbar', () => {
        render(<App />);
        expect(screen.getByText('Travel System')).toBeInTheDocument();
    });
});
