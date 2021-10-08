
## Lectura de datos
costo10Astar <- read.csv("datasets_exp_num/aestrella_resultadosMuestra10.csv", FALSE, ';')
colnames(costo10Astar) <- c("consumo_petroleo", "glp_no_entregado", 
                            "glp_pedido", "pedidos_no_entreg", "total_pedidos")
costo10Astar$cons_petro_glp_ne <-  costo10Astar$consumo_petroleo + costo10Astar$glp_no_entregado

costo100Astar <- read.csv("datasets_exp_num/aestrella_resultadosMuestra100.csv", FALSE, ';')
colnames(costo100Astar) <- c("consumo_petroleo", "glp_no_entregado", 
                            "glp_pedido", "pedidos_no_entreg", "total_pedidos")
costo100Astar$cons_petro_glp_ne <-  costo100Astar$consumo_petroleo + costo100Astar$glp_no_entregado

costo200Astar <- read.csv("datasets_exp_num/aestrella_resultadosMuestra200.csv", FALSE, ';')
colnames(costo200Astar) <- c("consumo_petroleo", "glp_no_entregado", 
                            "glp_pedido", "pedidos_no_entreg", "total_pedidos")
costo200Astar$cons_petro_glp_ne <-  costo200Astar$consumo_petroleo + costo200Astar$glp_no_entregado


costo10Agen <- read.csv("datasets_exp_num/agen_resultadosMuestra10.csv", FALSE, ';')
colnames(costo10Agen) <- c("consumo_petroleo", "glp_no_entregado", 
                            "glp_pedido", "pedidos_no_entreg", "total_pedidos")
costo10Agen$cons_petro_glp_ne <- costo10Agen$consumo_petroleo + costo10Agen$glp_no_entregado

costo100Agen <- read.csv("datasets_exp_num/agen_resultadosMuestra100.csv", FALSE, ';')
colnames(costo100Agen) <- c("consumo_petroleo", "glp_no_entregado", 
                            "glp_pedido", "pedidos_no_entreg", "total_pedidos")
costo100Agen$cons_petro_glp_ne <-  costo100Agen$consumo_petroleo + costo100Agen$glp_no_entregado

costo200Agen <- read.csv("datasets_exp_num/agen_resultadosMuestra200.csv", FALSE, ';')
colnames(costo200Agen) <- c("consumo_petroleo", "glp_no_entregado", 
                            "glp_pedido", "pedidos_no_entreg", "total_pedidos")
costo200Agen$cons_petro_glp_ne <-  costo200Agen$consumo_petroleo + costo200Agen$glp_no_entregado


variable <- "cons_petro_glp_ne" #glp_no_entregado
variable <- "glp_no_entregado"


x_lab = "Costo Total: Petroleo consumido + m3 de GLP no entregado"
x_lab = "Metros cubicos de GLP no entregados"


# Histogramas costo algoritmo A estrella
hist(costo10Astar[[variable]],
     main = "Algoritmo A estrella",
     xlab = x_lab,
     col="blue")

hist(costo100Astar[[variable]],
     main = "Algoritmo A estrella",
     xlab = x_lab,
     col="blue")

hist(costo200Astar[[variable]],
     main = "Algoritmo A estrella",
     xlab = x_lab,
     col="blue")

# Histogramas costo algoritmo A genetico
hist(costo10Agen[[variable]],
     main = "Algoritmo Genetico",
     xlab = x_lab,
     col="blue")

hist(costo100Agen[[variable]],
     main = "Algoritmo Genetico",
     xlab = x_lab,
     col="blue")

hist(costo200Agen[[variable]],
     main = "Algoritmo Genetico",
     xlab = x_lab,
     col="blue")



#pruebas A estrella - 10 pedidos
shapiro.test(costo10Astar[[variable]])
shapiro.test(costo10Agen[[variable]])

var.test(costo10Astar[[variable]], costo10Agen[[variable]], alternative = "two.sided")


z.test(costo10Astar[[variable]], costo10Agen[[variable]], alternative = "two.sided",mu = 0, 
       sigma.x =sd(costo10Astar[[variable]]), sigma.y = sd(costo10Agen[[variable]]))

z.test(costo10Astar[[variable]], costo10Agen[[variable]], alternative = "greater",mu = 0, 
       sigma.x =sd(costo10Astar[[variable]]), sigma.y = sd(costo10Agen[[variable]]))


#pruebas A estrella - 100 pedidos
shapiro.test(costo100Astar[[variable]])
shapiro.test(costo100Agen[[variable]])

var.test(costo100Astar[[variable]], costo100Agen[[variable]], alternative = "two.sided")


z.test(costo100Astar[[variable]], costo100Agen[[variable]], alternative = "two.sided",mu = 0, 
       sigma.x =sd(costo100Astar[[variable]]), sigma.y = sd(costo100Agen[[variable]]))

z.test(costo100Astar[[variable]], costo100Agen[[variable]], alternative = "greater",mu = 0, 
       sigma.x =sd(costo100Astar[[variable]]), sigma.y = sd(costo100Agen[[variable]]))


#pruebas A estrella - 200 pedidos
shapiro.test(costo200Astar[[variable]])
shapiro.test(costo200Agen[[variable]])

var.test(costo200Astar[[variable]], costo200Agen[[variable]], alternative = "two.sided")


z.test(costo200Astar[[variable]], costo200Agen[[variable]], alternative = "two.sided",mu = 0, 
       sigma.x =sd(costo200Astar[[variable]]), sigma.y = sd(costo200Agen[[variable]]))

z.test(costo200Astar[[variable]], costo200Agen[[variable]], alternative = "greater",mu = 0, 
       sigma.x =sd(costo200Astar[[variable]]), sigma.y = sd(costo200Agen[[variable]]))



