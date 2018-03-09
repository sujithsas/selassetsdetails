# SEL ASSET DETAIL API

API to Retrieve,Create,Delete,Modify the asset information

Setting up the Project

1) Have maven install and Java JDK 8 Installed in the development machine.
2) Import the Project as Maven project to your IDE of your choice.
3) Run the spring-boot:run to run the application.


# API Documentation

**Show all Asset**
----
  Returns json data about all the assets.

* **URL**

  /api/asset/

* **Method:**

  `GET`

*  **URL Params**

  None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 200 <br />
    **Content:** `[{
                          "id": 1,
                          "assetName": "SEL-5045 acSELerator Team Software",
                          "assetType": "Collection",
                          "details": "Improve productivity, manage device data, and collect data quickly and efficiently with Team Software."
                      },
                      ]`

* **Error Response:**

  * **Code:** 204 NO CONTENT <br />


**Show a Single Asset**
 ----
   Returns json data about a single asset.

 * **URL**

   /api/asset/{id}

 * **Method:**

   `GET`

 *  **URL Params**

  **Required:**

     `id=[integer]`

 * **Data Params**

   None

 * **Success Response:**

   * **Code:** 200 <br />
     **Content:** `{       "id": 1,
                           "assetName": "SEL-5045 acSELerator Team Software",
                           "assetType": "Collection",
                           "details": "Improve productivity, manage device data, and collect data quickly and efficiently with Team Software."
                       },

 * **Error Response:**

  * **Code:** 404 NOT FOUND <br />
  {
      "errorMessage": "Asset with id {} not found"
  }
  
**Create an Asset**
 ----

  Returns json data about all the assets.

* **URL**

  /api/asset/

* **Method:**

  `POST`

*  **URL Params**

  None

* **Data Params**

  JSON Payload {
    	"assetName": "SEL-5043 acSELerator Team Software",
        "assetType": "Collection4",
        "details": "Improve productivity, manage device data, and collect data quickly and efficiently with Team Software."
    }

* **Success Response:**

  * **Code:** 201 CREATED <br />
   

* **Error Response:**

* **Code:** 409 CONFLICT<br />
{"errorMessage":"Unable to create. An asset with name SEL-5041 acSELerator Team Software already exist."}


**Update an Asset**
 ----  
  Returns json data about the modfied asset

 * **URL**

   /api/asset/{id}

 * **Method:**

   `PUT`

 *  **URL Params**

  **Required:**

     `id=[integer]`

 * **Data Params**

   JSON PAYLOAD 
	{
    	"assetName": "SEL-5043 acSELerator Team Software",
        "assetType": "Collection4",
        "details": "Improve productivity, manage device data, and collect data quickly and efficiently with Team Software."
    }
   

 * **Success Response:**

   * **Code:** 200 OK <br />     
	**Content:**	{       "id": 1,
                           "assetName": "SEL-5045 acSELerator Team Software",
                           "assetType": "Collection",
                           "details": "Improve productivity, manage device data, and collect data quickly and efficiently with Team Software."
                       }
		
 * **Error Response:**
		
  * **Code:** 404 NOT FOUND <br />
  {"errorMessage":"Unable to update. Asset with id {} not found."}

**Delete a single Asset**
 ----  
  Returns deletes a single asset and return No Content response

 * **URL**

   /api/asset/{id}

 * **Method:**

   `DELETE`

 *  **URL Params**

  **Required:**

     `id=[integer]`

 * **Data Params**

   None

 * **Success Response:**

   * **Code:** 204 NO CONTENT <br />     

 * **Error Response:**

  * **Code:** 404 NOT FOUND <br />
  {"errorMessage":"Unable to delete. Asset with id 1 not found."}
  
**Delete all Asset**
 ----  

  Returns Deletes all the asset and return No Content response

* **URL**

  /api/asset/

* **Method:**

  `DELETE`

*  **URL Params**

  None

* **Data Params**

  None

* **Success Response:**

  * **Code:** 204 NO CONTENT <br />
   
