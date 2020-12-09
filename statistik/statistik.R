data = read.csv2("data.csv")

correct = data[data$levelName == "school-outside",]$correct 
hist(correct)

qqnorm(correct)
qqline(correct)

boxplot(data$correct ~ data$levelName)

aov.res = aov(data$correct ~ data$levelName)
summary(aov.res)

tukey.res = TukeyHSD(aov.res)
plot(tukey.res)

schoolScores = data[data$levelName == "school-outside",]$score
supermarketScores = data[data$levelName == "supermarket",]$score

df_val = min(length(schoolScores), length(supermarketScores)) - 1

SE = sqrt((sd(schoolScores)**2)/length(schoolScores) + (sd(supermarketScores)**2)/length(supermarketScores))

q_val = (mean(schoolScores) - mean(supermarketScores) - 0) / SE

p_val = (1 - pt(q = q_val, df = df_val)) * 2

