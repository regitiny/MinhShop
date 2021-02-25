import React from 'react';
import {NavLink} from 'react-router-dom';

export  const HistoryView=(props)=>
{
  const jsonViews: any=localStorage.getItem('product')
  const views=JSON.parse(jsonViews)
  window.console.log(views)
  return (
    <div className="view d-flex">
      <div className='col-9 d-flex'>
        {views && views.length>0?views.map((item)=>(
            <NavLink key={item.id} to={item.url} className='col-2'>
              <div>
                <img src={item.image} width='100%'/>
                <h5>{item.name}</h5>
              </div>
            </NavLink>
          )
        ): ''}
      </div>
    </div>
  );
}

export default HistoryView;