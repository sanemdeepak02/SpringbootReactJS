import React from 'react';
import './../App.css';

const DisplayTable = ({ data, selectedRows, checklistSelection }) => {
    const headers = data.length > 0 ?Object.keys(data[0]) : [];
    return (
        <table>
            <thead>
                <tr>
                    <th>Reserve</th>
                    {headers.map(header => (
                        <th key = {header} >{header.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase())}</th>
                    ))}
                </tr>
            </thead>
            <tbody>
                    {data.map((item, index) => (
                        <tr key ={index} className = {selectedRows.includes(item.personNumber) ? 'highlight' : ''}>
                            <td>
                                <input
                                    type = "checkbox"
                                    checked = {selectedRows.includes(item.personNumber)}
                                    onChange={() => checklistSelection(item.personNumber)}
                                    disabled ={item.reserve_By !== null}
                                />
                            </td>
                            {headers.map((col) => (
                                <td key ={`${col}-${index}`}>{item[col]}</td>
                            ))}
                        </tr>                    
                ))}
                {data.length === 0 && (
                    <tr>
                        <td colSpan = {headers.length + 1}>No employee records found for this search</td>
                    </tr>
                )}
            </tbody>
        </table>
    );
};
export default DisplayTable;



















