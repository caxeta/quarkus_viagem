import React, { useEffect, useState } from 'react';
import { getFlights } from '../api';
import { PlaneTakeoff, PlaneLanding } from 'lucide-react';

const FlightList = () => {
    const [flights, setFlights] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        getFlights()
            .then(response => {
                setFlights(response.data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching flights:', err);
                setError('Failed to load flights');
                setLoading(false);
            });
    }, []);

    if (loading) return <div className="loading-container"><div className="spinner"></div></div>;
    if (error) return <div className="error">{error}</div>;

    return (
        <div>
            <div className="section-header">
                <h2 className="section-title">Scheduled Flights</h2>
                <div className="badge">{flights.length} Flights</div>
            </div>

            <div className="card-grid">
                {flights.map(flight => (
                    <div key={flight.id} className="card">
                        <div className="card-header">
                            <h3>Flight #{flight.id}</h3>
                        </div>
                        <div className="card-body">
                            <div className="info-row">
                                <span className="info-label">Order ID</span>
                                <span>{flight.travelOrderId}</span>
                            </div>
                            <div className="info-row">
                                <PlaneTakeoff size={18} />
                                <span className="info-label">From</span>
                                <span>{flight.fromAirport}</span>
                            </div>
                            <div className="info-row">
                                <PlaneLanding size={18} />
                                <span className="info-label">To</span>
                                <span>{flight.toAirport}</span>
                            </div>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default FlightList;
