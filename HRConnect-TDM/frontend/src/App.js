import './App.css';
import axios from 'axios';
import React, { useState, useEffect } from 'react';
import Dropdown from './components/dropdown';
import DisplayTable from './components/table';

const App = () => {
  const [data, setData] = useState([]); // will hold the transformed fetched data
  const [allEnvs, setAllEnvs] = useState([]);
  const [selectedRows, setSelectedRows] = useState([]);

  const [filters, setFilters] = useState({
    envName: '',
    legalEmprName: '',
    buName: '',
    colAgreementName: ''
  });

  // Function to fetch data from the new API
  useEffect(() => {
    axios.get('http://localhost:8080/env')
      .then(response => {
        //console.log(response.data);
        // Transform the fetched data to flatten it and include the environment name
        setAllEnvs(response.data);
        const transformedData = response.data.flatMap(env => env.employee.map(emp => ({...emp, envName: env.envName})));
        //console.log("Transformed data", transformedData);
        setData(transformedData); // store the transformed data in state
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []);

  // Function to handle selecting a dropdown value
  const handleSelect = (filterType, value) => {
    const stateKey = {
      'Environment Name': 'envName',
      'Legal Employer Name': 'legalEmprName',
      'Business Unit Name': 'buName',
      'Collective Agreement Name': 'colAgreementName'
    }[filterType];

    setFilters(prevFilters => {
      const newFilters = {
        ...prevFilters,
        [stateKey]: value
      };

      console.log(newFilters);
      return newFilters;
    });
  };

  // Function to filter data based on the selected dropdown values
  const getFilteredData = () => {
    console.log("Filters:", filters);
    const filteredData= data.filter(item => {
      return (
        (filters.envName === '' || item.envName === filters.envName) &&
        (filters.legalEmprName === '' || item.legalEmprName === filters.legalEmprName) &&
        (filters.buName === '' || item.buName === filters.buName) &&
        (filters.colAgreementName === '' || item.colAgreementName === filters.colAgreementName)
      );
    });
    //console.log("Filtered data", filteredData);
    return filteredData;
  };

  const uniqueValues = (key) => {
    if (key === 'envName') {
      // Get unique envName values from all environments
      const uniqueVals = [...new Set(allEnvs.map(env => env.envName))];
      return uniqueVals;
    } else {
      // Get unique values from the employee data
      const uniqueVals = [...new Set(data.map(item => item[key]))];
      return uniqueVals;
    }
  };

  // Function to handle the checklist selection of a row
  const checklistSelection = (personNumber) => {
    setSelectedRows(prevSelectedRows => {
      if (prevSelectedRows.includes(personNumber)) {
        return prevSelectedRows.filter(num => num !== personNumber);
      } else {
        return [...prevSelectedRows, personNumber];
      }
    });
  };

  const handleSubmit = () => {
    // Add any action to be performed when clicked the button, like sending them to an API
    alert("This feature is under development");
  };

  return (
    <div id="container">
      <div id="container2">
        <Dropdown label="Environment Name" options={uniqueValues('envName')} onSelect={handleSelect} />
        <Dropdown label="Business Unit Name" options={uniqueValues('buName')} onSelect={handleSelect} />
        <Dropdown label="Legal Employer Name" options={uniqueValues('legalEmprName')} onSelect={handleSelect} />
        <Dropdown label="Collective Agreement Name" options={uniqueValues('colAgreementName')} onSelect={handleSelect} />
      </div>
      <div className='table-container'>
        <DisplayTable
          data={getFilteredData()}
          selectedRows={selectedRows}
          checklistSelection={checklistSelection}
        />
      </div>
      <button id="button" onClick={handleSubmit}>Submit</button>
    </div>
  );
};

export default App;


