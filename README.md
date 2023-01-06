# Getting Started

### SearchbookAPI

### How to install  

To install the project, "mvn clean install" should suffice. To start use "mvn spring-boot:run"

### The endpoint
Once started the endpoint /findBooksByText should be available. 
It can be called using a GET request. 

In the getRequest there are two parameters.
- **searchText** : This is the text send to the GoogleBooksAPI and searches in a broad way for the keywords given. 


- **searchLanguage** : This parameter takes a String with min and max two characters. It is used to indicate the language in which the books are published. The API takes any two letter combinations, but to get the desired results it should comply to the ISO6391-1 standard. 

**Example:**
SearchText: "Jane+Austen"
Language: "en"
URL: http://localhost:8080/findBooksByText?searchText=jane+Austen&searchLanguage=en

Alternatively the "frontend" can be used and should be available at:
URL: http://localhost:8080

The result of this call is a 10-row date-sorted list. 

It is a list of "Items". Each item has a "VolumeInfo" which contains the specifics for a booktitle that has been found. The "formattedPublishedDate" shows the date (if available) as follows: "DD Month YYYY". The month is displayed in the Locale currently configured (in my case Dutch). 

    "items": [
        {
            "volumeInfo": {
                "title": "Colonel Brandon in His Own Words",
                "authors": [
                    "Shannon Winslow",
                    "Jane Austen"
                ],
                "industryIdentifiers": [
                    {
                        "type": "ISBN_10",
                        "identifier": "0989025985"
                    },
                    {
                        "type": "ISBN_13",
                        "identifier": "9780989025980"
                    }
                ],
                "language": "en",
                "formattedPublishedDate": "25 juni 2022",
                "publishedDate": "2022-06-25"
            }
        },....

### Used Framework
Spring boot has been used to create this api.