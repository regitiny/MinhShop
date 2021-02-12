import { ADD_TO_CART, UPDATE_IN_CART, REMOVE_FROM_CART, DELETE_CART } from './../actions/types';

import { ICartShop, defaultValue } from 'app/shared/model/cart-shop.model';

const localState = JSON.parse(localStorage.getItem('cartItems'));

const dataState = { cartItems: localState };

const defaultCartState = {
  cartItems: [] as ReadonlyArray<ICartShop>,
  cartItem: defaultValue,
};

const initialState = dataState.cartItems && dataState.cartItems.cartItems !== null ? dataState : defaultCartState;
export type CartState = Readonly<typeof initialState>;
// Reducer
export default (state: CartState = initialState, action): CartState => {
  switch (action.type) {
    case ADD_TO_CART:
      return {
        cartItems: action.payload.cartItems,
      };
    case UPDATE_IN_CART:
      return {
        cartItems: action.payload.cartItems,
      };
    case REMOVE_FROM_CART:
      return {
        cartItems: action.payload.cartItems,
      };
    case DELETE_CART:
      return {
        cartItems: [],
      };
    default:
      return state;
  }
};
