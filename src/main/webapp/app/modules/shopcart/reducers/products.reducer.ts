import { FETCH_PRODUCTS } from 'app/modules/shopcart/actions/types';
import { defaultValue, IProducts } from 'app/shared/model/products';

const initialState = {
  products: [] as ReadonlyArray<IProducts>,
  product: defaultValue,
};
export type ProductState = Readonly<typeof initialState>;

// Reducer
export default (state: ProductState = initialState, action): ProductState => {
  switch (action.type) {
    case FETCH_PRODUCTS:
      return {
        ...state,
        products: action.payload,
      };
    default:
      return state;
  }
};
