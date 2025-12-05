import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Navbar from './components/Navbar';
import TripList from './components/TripList';
import HotelList from './components/HotelList';
import FlightList from './components/FlightList';

function App() {
  return (
    <Router>
      <div className="container">
        <Navbar />
        <Routes>
          <Route path="/" element={<TripList />} />
          <Route path="/hotels" element={<HotelList />} />
          <Route path="/flights" element={<FlightList />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
