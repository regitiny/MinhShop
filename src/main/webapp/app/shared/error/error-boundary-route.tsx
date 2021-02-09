import React, { useEffect } from 'react';
import { Route, RouteProps } from 'react-router-dom';
import ErrorBoundary from 'app/shared/error/error-boundary';

export const ErrorBoundaryRoute = ({ component: Component, ...rest }: RouteProps) => {
  const encloseInErrorBoundary = props => (
    <ErrorBoundary>
      <Component {...props} />
    </ErrorBoundary>
  );

  if (!Component) throw new Error(`A component needs to be specified for path ${(rest as any).path}`);

  return <Route {...rest} render={encloseInErrorBoundary} />;
};

export default ErrorBoundaryRoute;

// import React,{useEffect} from 'react';
// import { Route, RouteProps } from 'react-router-dom';
// import ErrorBoundary from 'app/shared/error/error-boundary';
//
// interface ErrorBoundaryRouteProps extends RouteProps //todo add 8/2/2021
// {
//   title: string;
// }
// export const ErrorBoundaryRoute = (props:ErrorBoundaryRouteProps) => {
// // export const ErrorBoundaryRoute = ({ component: Component, ...rest}: ErrorBoundaryRouteProps) => {
//   useEffect(() => {
//     document.title = "Website name | " + props.title;
//   });
//
//   const { title, ...rest } = props;
//
//   return <Route {...rest}  />;
// };
//
// export default ErrorBoundaryRoute;
