export const ProductViews =(productView)=>{
  const Views=JSON.parse(localStorage.getItem('product'));
  const productViews=Views?Views:[];
  let alreadyExist=false
  if(productView){
    productViews.forEach(x =>
  {
    if (x && x.id === productView.id)
    {
      alreadyExist = true;
    }
  });
  if (!alreadyExist && productView)
  {
    window.console.log(!alreadyExist);
    productViews.push(productView);
  }
  window.console.log(productViews)
  localStorage.setItem('product',JSON.stringify(productViews))
}
}