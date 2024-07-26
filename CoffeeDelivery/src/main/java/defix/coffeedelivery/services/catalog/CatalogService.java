package defix.coffeedelivery.services.catalog;

import defix.coffeedelivery.services.basket.BasketService;
import defix.coffeedelivery.services.filter.FilterService;
import defix.coffeedelivery.services.filter.SearchService;
import defix.coffeedelivery.services.product.ProductDTO;
import defix.coffeedelivery.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CatalogService {
    private final FilterService filterService;
    private final BasketService basketService;
    private final SearchService searchService;

    @Autowired
    public CatalogService(FilterService filterService, BasketService basketService, SearchService searchService) {
        this.filterService = filterService;
        this.basketService = basketService;
        this.searchService = searchService;
    }

    public void changeFilterType(final String filterType) {
        filterService.changeType(filterType);
    }

    public void changeFilterMinPrice(final Integer filterMinPrice) {
        filterService.changeMinPrice(filterMinPrice);
    }

    public void changeFilterMaxPrice(final Integer filterMaxPrice) {
        filterService.changeMaxPrice(filterMaxPrice);
    }

    public Map<Integer, ProductDTO> getSearchItems(final String searchValue) {
        filterService.changeQueryName(searchValue);
        return ProductService.prepareProductsToResponse(searchService.search());
    }

    public void addProductToBasket(final Integer productId) {
        basketService.addProductToBasket(productId);
    }
}
