import './laptop.scss'
import React, {useState, useEffect} from 'react';
import axios from 'axios';
import {Storage, Translate, getSortState, JhiPagination, JhiItemCount} from 'react-jhipster';
import {
  Card,
  CardHeader,
  CardTitle,
  CardSubtitle,
  CardImg,
  CardBody,
  CardText,
  Progress,
  Button,
  Row
} from 'reactstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {Link, NavLink, RouteComponentProps} from 'react-router-dom';
import _ from 'lodash';
import {BreadcrumbsItem} from 'react-breadcrumbs-dynamic';
import {overridePaginationStateWithQueryParams} from 'app/shared/util/entity-utils';
import {ITEMS_PER_PAGE} from 'app/shared/util/pagination.constants';
import {connect} from 'react-redux';
import {IRootState} from 'app/shared/reducers';
import {reset} from 'app/entities/simple-post/simple-post.reducer';
import {getEntities as getTypePosts} from 'app/entities/type-post/type-post.reducer';

export interface ISimplePostProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }>
{
}

const Laptop = (props: ISimplePostProps) =>
{
  const [paginationState, setPaginationState] = useState(
    overridePaginationStateWithQueryParams(getSortState(props.location, ITEMS_PER_PAGE), props.location.search)
  );
  const Token = Storage.local.get('jhi-authenticationToken') || Storage.session.get('jhi-authenticationToken');

  const [laptops, setLaptops] = useState([]);
  const [totalLaptops, setTotalLaptops] = useState([]);
  const [randomNumbers, setRandumNumbers]=useState([]);
  const [randomUrls, setRandomUrls]=useState([])
  const [slideIndex, setSlideIndex]=useState(1)
  const authToken = `Bearer ${Token}`;
  useEffect(() =>
  {
    // axios({
    //   url: 'api/_search/simple-posts',
    //   method: 'get',
    //   headers: {
    //     Authorization: authToken,
    //   },
    //   params: { size: 20, page: 0, query: 'typePost.id:1051' },
    // }).then(res => setLaptops(res.data));
    async function getLaptop()
    {
      try
      {
        const response = await axios.get('api/_search/simple-posts', {
          headers: {Authorization: authToken},
          params: {page: paginationState.activePage - 1, size: paginationState.itemsPerPage, query: 'typePost.id:1051'},
        });
        const {data} = response;
        window.console.log(response);
        setLaptops(data);
      }
      catch (error)
      {
        window.console.log(error);
      }
    }

    getLaptop();
  }, [paginationState.activePage]);
  useEffect(() =>
  {
    axios({
      method: 'get',
      url: 'api/_search/simple-posts',
      headers: {Authorization: authToken},
      params: {query: 'typePost.id:1051'},
    }).then(res => setTotalLaptops(res.data));
  }, []);

  const totalItems = totalLaptops ? totalLaptops.length : 1;
  const handlePagination = currentPage =>
  {
    props.reset();
    setPaginationState({
      ...paginationState,
      activePage: currentPage,
    });
  };

  const generaterNumber=()=>{
    if(laptops && laptops.length>0){
      const randomNumber=Math.floor(Math.random()*laptops.length);
      if(randomNumbers.indexOf(randomNumber)<0){
        randomNumbers.push(randomNumber)
      }
      else generaterNumber()
      window.console.log(randomNumbers)
    }
  }
  useEffect(()=>{
      for(let i=0; i<5; i++){
        generaterNumber()
      }
    if(laptops && laptops.length>0)
      randomNumbers.map((item,index)=>{
        randomUrls.push({link: laptops[item].imageUrl, id: index.toString()})
      })
  },[laptops])
  window.console.log(randomNumbers)
  window.console.log(randomUrls)
  window.console.log(paginationState);

  const showRandomUrls=()=>{
    let result=null;
    if(randomUrls && randomUrls.length>0){
      result= randomUrls.map((item, index)=>{
        return(
          <div key={index} className="image-slide" style={{display: "none"}}>
            <img width="100%"src={item.link}/>
          </div>
        )
      })
    }
    return result
  }

  const showSlides=()=>{
    const slides = Array.from(document.getElementsByClassName('image-slide') as HTMLCollectionOf<HTMLElement>);
    for(let i=0; i<slides.length; i++){
      slides[i].style.display="none";
    }
    window.console.log(slideIndex)
    if(slides && slides.length>0){
      slides[slideIndex].style.display="block";
      window.console.log(slides[slideIndex].style.display)
    }

  }
  useEffect(()=>{
    const upIndex=setTimeout(()=>{
      if(slideIndex===0){
        clearTimeout(upIndex)
      }
      if(slideIndex===4){
        setSlideIndex(0)
      }
      else setSlideIndex(slideIndex+1)
    }, 1500)
    return ()=>{
      clearTimeout(upIndex)
      showSlides()
    }
  },[slideIndex])

  // useEffect(()=>{
  //   props.getEntities(paginationState.activePage - 1, paginationState.itemsPerPage, 'typePost.id:1051');
  // },[])
  window.console.log(laptops);
  return (
    <div>
      <div className="header-banner d-flex justify-content-center">
        <div className="d-flex row col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
          <div className="col-8 bg-success text-center text-white">
            {showRandomUrls()}
          </div>
          <div className="col-4 bg-danger text-center text-white">banner</div>
        </div>
      </div>
      <div className="d-flex justify-content-center">
        <div className="d-flex row col-12 col-sm-11 -col-md-10 col-lg-10 col-xl-9">
          {laptops && laptops.length > 0
            ? // ? laptops
              //     .filter(laptop => laptop.typePost.typeName === 'Laptop')
            laptops.map(laptop =>
            {
              // if (laptop.typePost.typeName === 'Laptop') {}
              return (
                <div className="col-3 mt-3" key={laptop.uuid + laptop.id}>
                  {/*<Link to={`/${laptop.id}`}>*/}
                  <Link to={`${props.match.url}/${laptop.id}`} className="laptop-view">
                    <Card className="p p-sm-1 p-lg-0 ">
                      <CardHeader className="px-1 px-md-1 p-lg-2">
                        <div className='image-size'>
                          <CardImg top src={laptop.imageUrl} alt="Card image cap"/>
                        </div>
                        <div className="float-group laptop-title mt-1">
                          <CardTitle tag="h4" className="float-left text-dark mt-2">
                           {laptop.title}
                          </CardTitle>
                        </div>
                      </CardHeader>
                      <CardBody>
                        <CardText className="">
                          <p className="float-left">Giá gốc: </p>
                          <div className="float-left text-secondary ml-1">
                            <del>{laptop.price.toLocaleString()}đ</del>
                          </div>
                          <br/>
                        </CardText>
                        <CardText className="">
                          <p className="float-left">Mới: </p>
                          <div className="float-left text-success ml-1">
                            <b>{laptop.salePrice.toLocaleString()}đ</b>
                          </div>
                          <div className="float-left badge badge-danger text-white ml-2">-{laptop.percentSale.toFixed(0)}%</div>
                          <br/>
                        </CardText>
                        <div className="text-center" style={{width: '200px'}}>
                          <Progress animated value={laptop.scores}>
                            {laptop.scores}
                          </Progress>
                        </div>
                      </CardBody>
                    </Card>
                  </Link>
                </div>
              );
            })
            : null}
        </div>
      </div>
      {laptops.length ? (
        <div className={laptops && laptops.length > 0 ? '' : 'd-none'}>
          <Row className="justify-content-center">
            <JhiItemCount page={paginationState.activePage} total={totalItems}
                          itemsPerPage={paginationState.itemsPerPage} i18nEnabled/>
          </Row>
          <Row className="justify-content-center">
            <JhiPagination
              activePage={paginationState.activePage}
              onSelect={handlePagination}
              maxButtons={5}
              itemsPerPage={paginationState.itemsPerPage}
              totalItems={totalItems}
            />
          </Row>
        </div>
      ) : (
        ''
      )}
    </div>
  );
};
const mapStateToProps = (storeState: IRootState) => ({});

const mapDispatchToProps = {
  reset,
  getTypePosts,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;
export default connect(null, mapDispatchToProps)(Laptop);
