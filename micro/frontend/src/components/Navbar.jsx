import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { Plane, Hotel, Map } from 'lucide-react';

const Navbar = () => {
    const location = useLocation();

    const isActive = (path) => location.pathname === path ? 'active' : '';

    return (
        <nav className="navbar">
            <h1>Travel System</h1>
            <div className="nav-links">
                <Link to="/" className={`nav-link ${isActive('/')}`}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <Map size={20} />
                        Trips
                    </div>
                </Link>
                <Link to="/hotels" className={`nav-link ${isActive('/hotels')}`}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <Hotel size={20} />
                        Hotels
                    </div>
                </Link>
                <Link to="/flights" className={`nav-link ${isActive('/flights')}`}>
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <Plane size={20} />
                        Flights
                    </div>
                </Link>
            </div>
        </nav>
    );
};

export default Navbar;
