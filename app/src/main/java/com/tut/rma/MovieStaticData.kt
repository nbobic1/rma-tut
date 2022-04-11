package com.tut.rma
object MovieRepository {
    fun getFavoriteMovies() : List<Movie> {
        return favoriteMovies();
    }
    fun getRecentMovies() : List<Movie> {
        return recentMovies();
    }
}

fun favoriteMovies(): List<Movie> {
    return listOf(
        Movie(1,"Pride and prejudice",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
        "16.02.2005.","https://www.imdb.com/title/tt0414387/",
        "a",listOf("kama","neko90*","reko9"),listOf("ia9","agaggaa9g","gagaga9","gagaassee9")),Movie(2,"Moja iflm",
            "Sparks fly when spieets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2003.","https://www.imdb.com/title/tt0414387/",
            "c",listOf("kama7","neko7" ,"reko7"),listOf("ia7","agaggaa7g","7gagaga","7gagaassee")),Movie(3,"Pirati s kariva",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "a",listOf("kama3","neko3","reko3"),listOf("ia3","agaggaag3","gagag3a","3gagaassee")),Movie(4,"Moja zena",
            "Sparks fly when spirited Elizabeth Bennet meets single, rich, and proud Mr. Darcy. But Mr. Darcy reluctantly finds himself falling in love with a woman beneath his class. Can each overcome their own pride and prejudice?",
            "16.02.2005.","https://www.imdb.com/title/tt0414387/",
            "d",listOf("kama4","ne4ko","4reko"),listOf("5ia","5agaggaag","g5agaga","5gagaassee"))
//Dodajte filmove po želji
    )
}
fun recentMovies(): List<Movie> {
    return listOf(
        Movie(1,"The Contractor",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "d",listOf("kama","neko","reko"),listOf("ia","agaggaag","gagaga","gagaassee")),
        Movie(1,"Radi sve",
            "A discharged U.S. Special Forces sergeant, James Harper, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "b",listOf("kama2","neko2","reko2"),listOf("ia2","agaggaag2","gagaga2","gagaassee2")),
        Movie(1,"Pulp Fiction",
            "A discharged U.S. Special Forces sergeant, James Harper,pair of diner bandits, risks everything for his family when he joins a private contracting organization.",
            "01.04.2022.","https://www.imdb.com/title/tt10323676/",
            "crime",listOf("kama3","neko3","reko3"),listOf("ia3","agaggaag3","gagaga3","gagaassee3"))
//Dodajte filmove po želji
    )
}