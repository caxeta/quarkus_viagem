import React, { useEffect, useState } from 'react';
import { getTravelOrders } from '../api';
import { Plane, Hotel, ArrowRight } from 'lucide-react';

const TripList = () => {
    const [trips, setTrips] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        getTravelOrders()
            .then(response => {
                setTrips(response.data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching trips:', err);
                setError('Failed to load trips');
                setLoading(false);
            });
    }, []);

    if (loading) return <div className="loading-container"><div className="spinner"></div></div>;
    if (error) return <div className="error">{error}</div>;

    return (
        <div>
            <div className="section-header">
                <h2 className="section-title">Travel Orders</h2>
                <div className="badge">{trips.length} Trips</div>
            </div>

            {trips.length === 0 ? (
                <div className="empty-state">
                    <Plane size={48} style={{ marginBottom: '1rem', opacity: 0.5 }} />
                    <h3>No trips found</h3>
                    <p>Create a new travel order to get started.</p>
                </div>
            ) : (
                <div className="card-grid">
                    {trips.map((trip, index) => (
                        <div key={index} className="card">
                            <div className="card-header">
                                <h3>Trip #{index + 1}</h3>
                            </div>
                            <div className="card-body">
                                <div className="info-row">
                                    <Plane size={18} className="text-accent" />
                                    <span className="info-label">Flight</span>
                                    <span>{trip.fromAirport} <ArrowRight size={14} style={{ margin: '0 4px' }} /> {trip.toAirport}</span>
                                </div>

                                <div className="info-row">
                                    <Hotel size={18} className="text-accent" />
                                    <span className="info-label">Hotel</span>
                                    <span>{trip.nights} Nights stay</span>
                                </div>
                            </div>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
};

export default TripList;
