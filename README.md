
# ShoppingPOC

This POC has covered below topics
- Kotlin
- Rxjava2
- Retrofit2
- Kotn
- Clean Architecture
- MVVM
- LiveData
- Data Binding
- Unit Testing(MOCKK)

## Code setup

- Step 1: Go to https://mocki.io/ 
- Step 2: Past below json and create mock API
- setp 3: Go to Code base (com.example.lillypoc.data.remote.StoreApi) and change your endpoint

#### StoreApi

```Kotlin
@GET("40561851-206f-4c6e-b2f1-034997395562")
    fun getStoreProducts():Single<StoreProducts>

```

#### Mock json

```Json
{
	"storeinfo": {
		"name": "My Fashion",
		"address": "Chennai-603103, TamilNadu, India",
		"logo": "https://png.pngtree.com/template/20190801/ourmid/pngtree-home-store-logo-design-inspiration-image_286517.jpg",
		"storeID": "CX001"
	},
	"products": [{
		"name": "Jeens",
		"image": "https://img-static.tradesy.com/item/30303586/blue-distressed-raw-hem-denim-soft-skinny-jeans-size-10-0-2-650-650.jpg",
		"price": 450,
		"availableQty": 20,
		"productID": "PX001"
	}, {
		"name": "T-shirt",
		"image": "https://5.imimg.com/data5/LG/AM/OW/SELLER-31619481/plain-t-shirt-500x500.jpeg",
		"price": 250,
		"availableQty": 50,
		"productID": "PX002"
	}, {
		"name": "Shoe",
		"image": "https://m.media-amazon.com/images/I/71D9ImsvEtL._UY500_.jpg",
		"price": 720,
		"availableQty": 10,
		"productID": "PX003"
	}, {
		"name": "Towel",
		"image": "https://5.imimg.com/data5/TZ/DL/HY/SELLER-32307656/cotton-bath-towel-500x500.jpg",
		"price": 150,
		"availableQty": 100,
		"productID": "PX004"
	}]
}
```


