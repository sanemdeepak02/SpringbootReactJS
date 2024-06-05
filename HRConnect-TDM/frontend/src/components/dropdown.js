

import React from "react";

const Dropdown = ({ label, options, onSelect }) => {
    return (
        <div className = "dropdown">
            <label htmlFor = {label}>{label}:</label>
            <select id = {label} onChange={(e) => onSelect(label, e.target.value)}>
                <option value = ""> Select {label}</option>
                {options.map((option, index) => (
                    <option key = {index} value = {option}>{option}</option>
                ))}
            </select>
        </div>
    );  
};

export default Dropdown;





