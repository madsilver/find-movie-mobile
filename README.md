# Find Movie Mobile
Serviço mobile de busca de informações sobre filmes

# Find Movie Mobile
Find Movie Mobile é um serviço mobile de busca de informações sobre filmes. O aplicativo é muito simples e intuitivo, na barra superior da tela inicial existe um campo de busca que fará a consulta na base de dados da OMDb API (http://www.omdbapi.com/). O retorno da busca é um JSON que será usado para preencher os dados da lista disponíveis em cards. Ao clicar em um card uma nova tela é aberta trazendo detalhes do filme.

## Arquitetura
O projeto foi desenvolvido na IDE Android Studio. O aplicativo é compatível com Android 4.0 (API level 15) ou superior. O compileSdkVersion e o targetSdkVersion estão definidos para o API 23, o minSdkVersion está definido para o API 15.

## Material Design
Os layouts foram criados baseados no Material Design e utilizam o melhor da linguagem de design, presentes nas cores, ícones, profundidade dos elementos e transições de telas.

# Bibliotecas

### OkHttp
Utilizado nas requisições ao servidor, facilita a leitura de conteúdo de texto (JSON,XML,HTML etc), faz cache de requisições repetidas e compactação das respostas com GZIP.

## GSON
Utilizado para fazer o parser do retorno JSON do servidor em um objeto.

## AsyncTask
Utilizada para realizar as operações de rede em uma thread separada.

## Picasso
Utilizado para carregar imagens da web.

## ButterKnife
Utilizado para injetar Views em componentes android.

## Data Binding
Utilizada para inserir código java no arquivo de layout, permitindo uma ligação e atualizaçãoautomática entre as classes de modelo e a view.


# Componentes

## RecyclerView
Utilizada como contêiner para o conjunto de views, podendo ser recicladas eficientemente. A RecyclerView é uma versão mais avançada e flexível da ListView.

## CardView
O CardVew é basicamente um FrameLayout com bordas arrendodas, background e umas sombra.

## CoordinatorLayout
Mais um componente que se comporta com um FrameLayout mas com capacidade de controlar animações em componentes inseridos nele.