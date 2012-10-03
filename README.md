## SearchBox.io Sample Scala Lift Application.

This example illustrates basic search features of SearchBox.io ([ElasticSearch](http://www.elasticsearch.org) as service).

Sample application is using [Jest](https://github.com/searchbox-io/Jest) Java/Scala ElasticSearch client to integrate with SearchBox.io.

To create initial index and sample data click "Create Documents" (2 sample articles will be created.)

To test search; enter "starcraft", "warcraft" or "*" to search box at top right and hit enter.

## Using with SearchBox.io

* Sign Up and get your API-KEY from [here](https://searchbox.io/users/sign_up).
* Replace "YOUR-API-KEY-HERE" with your API-KEY at DependencyFactory.scala


## Heroku Deployment

This sample can be deployed to Heroku with no change.

* Install SearchBox ElasticSearch Addon

* Deploy sample application and experience real time search.


## Local Setup

* Change connection string at DependencyFactory.scala
    * from
        ```
            servers.add(Properties.envOrElse("SEARCHBOX_URL", "https://api.searchbox.io/api-key/YOUR-API-KEY-HERE"))
        ```
    * to
        ```
            servers.add("http://localhost:9200")
        ```

To run example in your local environment with a local ElasticSearch instance via maven;

```
    mvn jetty:run
```