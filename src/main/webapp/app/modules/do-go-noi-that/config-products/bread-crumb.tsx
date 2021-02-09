import './configProducts.scss';
import React, { useState, useEffect } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Breadcrumb, BreadcrumbItem } from 'reactstrap';

const BreadCrumb = props => {
  const [title, setTitle] = useState('');
  const {
    history,
    location: { pathname },
  } = props;
  const pathnames = pathname.split('/').filter(x => x);

  useEffect(() => {
    setTitle(document.title);
  });
  const Items = [
    { to: '/', label: 'Home' },
    { to: './intro', label: 'Intro' },
    { to: './login', label: 'Login' },
    { to: './contact', label: 'Contact' },
  ];

  window.console.log(pathname);
  window.console.log(title);
  return (
    <div>
      <Breadcrumb tag="nav" listTag="div">
        {/*{pathnames && pathnames.length>0?(<BreadcrumbItem onClick={()=>history.push('/')} tag={Link} to={pathname}>{title}</BreadcrumbItem>):('')}*/}
        {/*<BreadcrumbItem BreadcrumbItem onClick={()=>history.push('/')} tag={Link} to={pathname}>{title}</BreadcrumbItem>*/}
        <BreadcrumbItem tag={Link} to="/">
          Home
        </BreadcrumbItem>
      </Breadcrumb>
    </div>
  );
};
export default withRouter(BreadCrumb);

// {pathnames.length > 0 ? (
//   <Link onClick={() => history.push("/")}>Home</Link>
// ) : (
//   <Typography> Home </Typography>
// )}
// {pathnames.map((name, index) => {
//   const routeTo = `/${pathnames.slice(0, index + 1).join("/")}`;
//   const isLast = index === pathnames.length - 1;
//   return isLast ? (
//     <Typography key={name}>{name}</Typography>
//   ) : (
//     <Link key={name} onClick={() => history.push(routeTo)}>
//       {name}
//     </Link>
//   );
// })}
