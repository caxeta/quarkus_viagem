import React, { useEffect, useState } from 'react';
import { getHotels } from '../api';
import { MapPin, Moon } from 'lucide-react';

const HotelList = () => {
    const [hotels, setHotels] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        getHotels()
            .then(response => {
                setHotels(response.data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching hotels:', err);
                setError('Failed to load hotels');
                setLoading(false);
            });
    }, []);

    if (loading) return <div className="loading-container"><div className="spinner"></div></div>;
    if (error) return <div className="error">{error}</div>;

    return (
        <div>
            <div className="section-header">
                <h2 className="section-title">Available Hotels</h2>
                <div className="badge">{hotels.length} Hotels</div>
            </div>

            <div className="card-grid">
                {hotels.map(hotel => (
                    <div key={hotel.id} className="card">
                        <div className="card-header">
                            <h3>Hotel #{hotel.id}</h3>
                        </div>
                        <div className="card-body">
                            <div className="info-row">
                                <MapPin size={18} />
                                <span className="info-label">Order ID</span>
                                <span>{hotel.travelOrderId}</span>
                            </div>
                            <div className="info-row">
                                <Moon size={18} />
                                <span className="info-label">Duration</span>
                                <span>{hotel.nights} Nights</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default HotelList;
