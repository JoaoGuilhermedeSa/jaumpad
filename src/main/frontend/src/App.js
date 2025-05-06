import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Home from "./pages/Home";
import NotePad from "./pages/NotePad";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/pad/:pageId" element={<NotePad />} />
      </Routes>
    </Router>
  );
}

export default App;
