# JavaAPI

# Template request

``` bash
# Request (GET)
/series

# Description
Renvoie le JSON associé à la collection de séries
```

``` bash
# Request (GET)
/series/{id}

# Description
Renvoie le JSON associé à une serie
```

``` bash
# Request (GET)
/series/{id}/games

# Description
Renvoie le JSON associé à la collection de games d'une série
```

``` bash
# Request (POST)
/series?token={token}

# Description
Crée une série à partir du JSON fourni

# Body
{
	"serie": {
		"name": "th",
        	"description": "Un quizz sur la ville de Nancy",
        	"city": "th",
		"coords": {
			"lat": "48.8",
			"lng": "6.6"
		},
		"pictures": [
			{
			"img": "001.png",
			"coords": {
				"lat": "48.44444444444444444444",
				"lng": "6.6"
				}
			},
			{
			"img": "002.png",
			"coords": {
				"lat": "48.8",
				"lng": "6.6"
				}
			},
			...
			{
			"img": "010.png",
			"coords": {
				"lat": "48.8",
				"lng": "6.6"
				}
			}
		]
	}
}
```


``` bash
# Request (PUT)
/series?token={token}

# Description
Ajoute des images dans une série à partir du JSON fourni

# Body
{
	 "pictures": [
      {
        "img": "003.jpeg",
        "coords": {
          "lat": "40.0",
          "lng": "6.0"
        }
      },
      ...
      {
        "img": "003.jpeg",
        "coords": {
          "lat": "40.0",
          "lng": "6.0"
      }
   }
}
```

``` bash
# Request (POST)
/series/{id}/upload?token={token}

# Description
Importe des images et les sauvegarde sur le serveur

# Body
headers: { 'content-type': 'multipart/form-data' }, formData
```

``` bash
# Request (PUT)
/series/{id}/upload?token={token}

# Description
Importe des image et les sauvegarde sur le serveur

# Body
headers: { 'content-type': 'multipart/form-data' }, formData
```

``` bash
# Request (GET)
/series/{id}/pictures/{pictures}

# Description
Renvoie l'image associé à l'id et au nom demandé en parametres

```

``` bash
# Request (POST)
/games

# Description
Crée une game à partir du JSON fourni

# Body
{
    "id_serie": "cfd653ef-002e-4bf6-85ef-0217f1aef892",
    "id_difficulty": "1",
    "nickname": "Steven"
}

# Response

Renvoie le token de la game
```

``` bash
# Request (PUT)
/games?token={game_token}

# Description
Rajoute le score d'une partie

# Body
{
	"score": "600"
}
```

``` bash
# Request (POST)
/signin

# Description
Authentifie un utilisateur à partir du JSON fourni

# Body
{
	"mail": "test@test.fr",
	"password": "test"
}
```

``` bash
# Request (POST)
/signup

# Description
Crée un compte pour un utilisateur à partir du JSON fourni

# Body
{
  "fullname" : "John Doe",
	"mail": "test@test.fr",
	"password": "test"
}
```
