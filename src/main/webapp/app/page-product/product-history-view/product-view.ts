export const ProductViews = (productView) =>
{
  const Views = JSON.parse(localStorage.getItem('product'));
  const productViews = Views ? Views : [];
  let alreadyExist = false
  if (productView !== null)
  {
    productViews.forEach(x =>
    {
      if (x && x.id === productView.id)
      {
        alreadyExist = true;
      }
    });
  }
  if (!alreadyExist)
  {
     productViews.push(productView)
  }
  // localStorage.setItem('product', JSON.stringify(productViews))
  localStorage.setItem('product', JSON.stringify(productViews))
}
