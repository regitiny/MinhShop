import './configProducts.scss';
import React from 'react';
import { NavLink, withRouter } from 'react-router-dom';
import { Breadcrumbs } from 'react-breadcrumbs-dynamic';

const BreadCrumb = props => {
  //   // const [title, setTitle] = useState('');
  //   // const {
  //   //   history,
  //   //   location: { pathname },
  //   // } = props;
  //   // const pathnames = pathname.split('/').filter(x => x);
  //   //
  //   // useEffect(() => {
  //   //   setTitle(document.title);
  //   // });
  //   const base_path="/"
  //   const Items = [
  //     { to: '/', label: 'Home' },
  //     { to: './intro', label: 'Intro' },
  //     { to: './login', label: 'Login' },
  //     { to: './contact', label: 'Contact' },
  //   ];
  //
  //   // window.console.log(pathname);
  //   window.console.log(history);
  //   return (
  //     <div>
  //       <BreadcrumbsItem to={base_path}>Trang Chủ</BreadcrumbsItem>
  //     </div>
  //   );
  // };
  const base_path = '/';
  return (
    <div className="hello-ngay-moi">
      {/*<BreadcrumbsItem to={base_path}>Trang Chủ</BreadcrumbsItem>*/}
      {/*<Breadcrumbs*/}
      {/*  item={CrumbItem}*/}
      {/*  container={Breadcrumb}*/}
      {/*  finalProps={{active: true}}*/}
      {/*  duplicateProps={{to: 'href'}}*/}
      {/*/>*/}
      <Breadcrumbs
        separator={<b> / </b>}
        item={NavLink}
        finalItem={'b'} //chọn thẻ tag cho route cuối cùng
        finalProps={{
          style: { color: 'red' },
        }}
      />
    </div>
  );
};

export default withRouter(BreadCrumb);
//
