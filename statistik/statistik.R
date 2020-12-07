data = read.csv2("data.csv")

boxplot(data$correct ~ data$levelName)

aov.res = aov(data$correct ~ data$levelName)
summary(aov.res)

tukey.res = TukeyHSD(aov.res)
plot(tukey.res)