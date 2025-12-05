import axios from 'axios';

const api = axios.create({
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getFlights = () => api.get('/api/flights');
export const getHotels = () => api.get('/api/hotels');
export const getTravelOrders = () => api.get('/api/travelorder');
export const createTravelOrder = (order) => api.post('/api/travelorder', order);

export default api;
