data = read.csv2("data.csv")

hist(data[data$levelName == "hospital-outside",]$score)

boxplot(data$score ~ data$levelName)

aov.res = aov(data$score ~ data$levelName)
summary(aov.res)

tukey.res = TukeyHSD(aov.res)
plot(tukey.res)
