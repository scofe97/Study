import {Route, Switch, Redirect, useLocation} from 'react-router-dom'
import Welcome from "./pages/Welcome";
import Products from "./pages/Products";
import MainHeader from "./components/MainHeader";
import ProductDetail from "./pages/ProductDetail";
import {Fragment} from "react";

function App() {

  const location = useLocation();
  console.log(location.pathname);

  return (
    <Fragment>
      <MainHeader/>
      <Switch>
        <main>
          <Route path='/' exact>
            <Redirect to='/welcome'/>
          </Route>
          <Route path="/welcome">
            <Welcome> </Welcome>
          </Route>
          <Route path="/products" exact>
            <Products> </Products>
          </Route>
          <Route path="/products/:productId">
            <ProductDetail/>
          </Route>
          <section> <p>{location.pathname}</p></section>
        </main>
      </Switch>
    </Fragment>
  );
}

export default App;
