import React from 'react';


const IngredientSelector = ({index, value, ingredients, deleteIngredient, onSelectorChange, onAmountChange}) => {
    return (
        <div key={index} className="containerIngredients">
            <div className="form-group mx-sm-3 mb-2" id="div1">
                <select className="form-control" id="category" name="category" onChange={e => onSelectorChange(index, e.target.value)}>
                    {ingredients.map(ingredient => (
                        <option value={ingredient.ingredientId}>{ingredient.name}</option>
                    ))}
                </select>
            </div>
            <div id="div2">
                <input min="0" type="number" className="form-control" onChange={e => onAmountChange(index, e.target.value)}/>
            </div>
            <div className="deleteBtn">
                <button type="button" className="btn btn-danger" onClick={() => deleteIngredient(index)}>
                    X
                </button>
            </div>
        </div>
    );
};

export default IngredientSelector;
