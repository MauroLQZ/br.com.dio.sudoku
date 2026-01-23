Repositorio do jogo Sudoku da dio por MauroLQZ.

- Projeto para criar um jogo de Sudoku em Java, implementando funcionalidades essenciais para um jogo interativo e funcional no terminal.
- O jogo Sudoku é formado por um tabuleiro com nove quadrados verticais e nove quadrados horizontal. 
- O jogo inicia com alguns quadrados preenchidos com numeros fixos que não podem ser alterados.
- O objetivo é preencher todos os quadrados restantes com a seguinte regra:
1- Dentro do quadrado de nove numeros, não pode repetir número.
2- Não pode repetir número na linha horizontal.
3- Também não pode repetir numero na vertical.
4- O erro acontece quando você repete um número dentro do quadrado segundo a regra, ou insere um número no espaço do número fixo.
5- O jogo termina quando você consegue preencher todos os quadrados sem erros.

- Foi dividido em duas partes:
1- Criar um sistema para interatividade do usario com o terminal.
2- Utilizar um ambiente grafico com swing.

1- Interatividade do usuario com o terminal:
- criar o pacote: br.com.dio

- Pacote util e a classe BoardTemplate para desenhar o tabuleiro.

- Pacote model com as classes Space e Board.
- A classe Space que representa espaço de cada quadrado, com os atributos int actual, int expected e boolean fixed.
- Com construtor que inicializa se o espaço é fixo com o número fixo ou não.
- Método clearValue para limpar o quadrado.
- Método reset para limpar todos os quadrados.
- A classe Board para colocar os modelos do tabuleiro.
- Com o atributo de uma lista com uma lista: List<List<Space> spaces. 
- O construtor inicializa esse List com ArrayList.
- O metodo getSpaces que retorna uma lista de espaços: List<List<Space>>.
- O metodo getSatus que verifica o status do jogo e retorna um enum gameStatusEnum com as opoções: NON_STARTED("Jogo não iniciado") - INCOMPLETE("Jogo incompleto") - COMPLETE("Jogo completo")
- O metodo que verifica se tem erros:public boolean hasErrors.
- O metodo que modifica o valor no espaço numero validos e não para numeros fixos: 
  public boolean changeValue.
- O metodo de limpeza do espaço:public boolean clearValue 
- O metodo de limpeza de todos os campos:public void reset.
- O metodo para finalizar o jogo:public boolean gameFinished.
 
- No pacote br.com.dio criar a classe Main para aplicar o jogo.
- Na classe Main pegar as posições do tabuleiro no argurmento args do metodo main.
- Com o argumento args montar o tabuleiro.
- Criar o menu com a opções para interagir com o jogo.
- Cada opção gera o metodo especifico para jogar.
- Metodo para iniciar o jogo recebendo como argumento as posições do tabuleiro:private static void startGame. 
- Metodo para digitar um numero, validando se pode ser inserido ou não(numero fixo): private static void inputNumber.
- Metodo para remover um numero: private static void removerNumber.
- Metodo para validar os numeros digitados entre 1 e 9:private static runUntilGetValidNumber.
- Metodo para visualizar o jogo:private static void showCurrentGame.
- Metodo para mostrar o status do jogo. public static void showGameStatus.
- Metodo para limpar o jogo: private static void clearGame
- Metodo finalizar o jogo: private static void finishGame.

2- Interatividade do usuario com o terminal:
- Foi criado o pacote service, que vai disponibilizar para interface grafica uma interação com nosso código.

- No pacote service foi criada as classes:
- classe BoardService: que vai ser usada para as funcionalidades para o projeto quebrar em camadas o serviço.
- atributo Board board: vai ser utilizado para o serviço.
- construtor que inicializa a variavel board com o metodo intitBoard.
- metodo initiBoard recebe como parametro um Map com a lista das posições do tabuleiro, que retorna essa lista.
- classe NotifierService: notificar um evento.
- enum EventEnum com CLEAR_SPACE rotulo do evento.
- interface eventListener para ouvir o evento com o metodo update.

- pacote ui.custom.button com as classes dos botões:
- checkGameStatusButton: botão que verifica o status do jogo.
- finishGameButton: botão que finaliza o jogo.
- resetButton: botão que reinicializa o jogo.

- pacote panel.
- classe SudokuSector: representa os nove setores do jogo com os quadradinhos.
- classe MainPanel: classe principal que vai guardar todos os componentes.

- pacote frame.
- classe MainFrame: o frame é a tela em si, todos os componentes.

- pacote input.
- classe NumberText:classe para trabalhar com a digitação dos numeros.
- classe NumberTextLimit:classe para validação da digitação dos numeros.

- pacote screen.
- classe MainScreen:classe que faz a composição da tela.
