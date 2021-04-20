## KSO-Test
### Simple App qui contient un écran qui affiche une liste d'éléments récuprés depuis un remote API et un écran détail qui contient le détail de chaque element et une map google avec des marqueurs.

 # Overview:
 
   <br/><br/>
    <div align = "center">
         <img src = "https://github.com/FdMed/KSO-repo/blob/master/screenshots/screen_1.png?raw=true" width="330">&ensp;&ensp;&ensp;
         <img src = "https://github.com/FdMed/KSO-repo/blob/master/screenshots/screen_2.png?raw=true" width="330">
        </div>


* ### __Model__
   Model est implémenté selon le pattern repository. Il recupère les données depuis le serveur (via Retrofit 2).
* ### __View__
   View est réalisé avec 2 fragments. la première contient un Recyclerview avec une liste d'éléments, la deuxième dépend du clique sur un élément de recyclerview et affiche le détail de l'élément avec une carte google map qui contient des marqueurs de deux positions ainsi que la position du device.
*  ### __ViewModel__
   ViewModel est résponsable de transfert des données entre le view et le model
* ### __Dagger 2__
    – Implement l'injection de dependece pour la communication entre les differents app module.<br/>
    – Simplifie les tests unitaire.
    <br/><br/>


# Technologies et libraries:


   * ### __Model__

    	* ### __Network__<br/>
    	   __Retrofit 2__ <br/>
    		      – récupère les données depuis le serveur vers les class data    
    	   __Gson__ <br/>
    		      – converte json aux objets

     * ### __Coroutines__ <br/>
            – gère les requettes réseau asynchrone via flow au lieu des callbacks<br/>
            – fournit des opèrations asynchrones légères

   * ### __ViewModel__
       * __LiveData__ <br/>
          – implément le pattern observer pour les interactions avec le view

   * ### __View__
       * __Fragments__ <br/>
         – display interactif

       *  __View binding__ <br/>
         – remplace les opérations basic du UI (e.g. findViewById() ) à XML

       *  __Navigation__ <br/>
         – navigation via des actions
         – pop les destinations depuis le backstack
         – argument - safe args transfère

   * ### __Tests unitaires__
       * __JUnit__ <br/>
         – Pour affectuer les diffrents assertions

       * __Mockito__ <br/>
         – Pour mocker les réponses du serveurs

## tout les outils: <br/>

   * Kotlin
   * Android X
   * Android Jetpack
   * Dagger 2
   * Kotlin Coroutines
   * Retrofit 2
   * Gson
   * JUnit
   * Mockito

