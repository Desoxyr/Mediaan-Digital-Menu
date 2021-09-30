import { render, screen } from '@testing-library/react';
import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';


test('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
  ReactDOM.unmountComponentAtNode(div);
});

// test if element exists
// test('renders Hello World', () => {
//   render(<App />);
//   const textElement = screen.getByText(/Hello World/i);
//   expect(textElement).toBeInTheDocument();
// });

// boolean test
describe('true is truthy and false is falsy', () => {
  test('true is truthy', () => {
    expect(true).toBe(true);
  });

  test('false is falsy', () => {
    expect(false).toBe(false);
  });
});

test('3 is 3', () => {
  expect(3).toBe(3);
});

test('text is text', () => {
  expect('text').toBe('text');
});

test('text not is tekst', () => {
  expect('text').not.toBe('tekst')
});
