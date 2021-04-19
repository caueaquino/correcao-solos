# Correção de Solos - Arquitetura de Software

## Análise do CLOC antes de melhorias

<pre>
github.com/AlDanial/cloc v 1.88  T=0.16 s (95.7 files/s, 4510.5 lines/s)
-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Java                             7            135              8            368
XML                              6              0              0            179
Maven                            1              3              0             13
Markdown                         1              0              0              1
-------------------------------------------------------------------------------
SUM:                            15            138              8            561
-------------------------------------------------------------------------------
</pre>


## Melhorias realizadas

1° - Foi adicionada enums para mapear os valores fixos do código e ter um melhor entendimento do funcionamento e a que esses valores se referem.
2° - Foi removida parte do código que não está sendo utilizada para facilitar e leitura e não deixar o código visualmente poluido.

As alterações realizadas podem ser vistas todas no link abaixo referêntes ao Pull request realizado e aprovado com as alterações citadas acima:
LINK PULL REQUEST: https://github.com/caueaquino/correcao-solos/pull/1


## Análise do CLOC após melhorias

<pre>
github.com/AlDanial/cloc v 1.88  T=0.13 s (137.4 files/s, 5238.1 lines/s)
-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Java                            10            111             22            341
XML                              6              0              0            179
Markdown                         1              2              0             15
Maven                            1              3              0             13
-------------------------------------------------------------------------------
SUM:                            18            116             22            548
-------------------------------------------------------------------------------
</pre>


Apesar de ainda conter muitas estruturas condicionais ainda no código, adicionado os enums nas condições dos switchs sendo os enums bem descritivos, fica bem facil de entender a que eles se referem e o funcionamento, apesar de essas estruturas tomarem boa parte do código. 

Pensei em utilizar hashmap utilizando um objeto para mapear todos os valores possiveis verificados dentro do switch porem a complexibilidade de entendimento do código seria maior em comparação com a clareza que o switch juntamente com os enums bem descritivos fornece. Por outro lado o desenpenho seria melhor e o tamanho do código menor, ja que não haveria todas aquelas comparações e com hashmap acessamos diretamente uma posição de memoria referente ao valor, não precisando fazer nenhum processamento em relação as condições do switch.
