data = read.csv2("data.csv")
data$levelName = factor(data$levelName, levels = c("supermarket", "hospital-outside", "school-outside"))

supermarketCorrect = data[data$levelName == "supermarket",]$correct
hist(supermarketCorrect, breaks = 12)


schoolCorrect = data[data$levelName == "school-outside",]$correct
hist(schoolCorrect, breaks = 12)

hospitalCorrect = data[data$levelName == "hospital-outside",]$correct
hist(hospitalCorrect, breaks = 12)

boxplot(data$correct ~ data$levelName)

par(mfrow=c(1,3))
qqnorm(supermarketCorrect, main = "Supermarket")
qqline(supermarketCorrect)

qqnorm(hospitalCorrect, main = "Hospital")
qqline(hospitalCorrect)

qqnorm(schoolCorrect, main = "School")
qqline(schoolCorrect)
par(mfrow=c(1,1))

var.test(schoolCorrect, supermarketCorrect)
var.test(hospitalCorrect, schoolCorrect)


supermarketMean = mean(supermarketCorrect)
hospitalMean = mean(hospitalCorrect)
schoolMean = mean(schoolCorrect)

dif_ss = schoolMean - supermarketMean;
df_val_ss = min(length(schoolCorrect), length(supermarketCorrect)) - 1
SE_ss = sqrt((sd(schoolCorrect)**2)/length(schoolCorrect) + (sd(supermarketCorrect)**2)/length(supermarketCorrect))
q_val_ss = (dif_ss - 0) / SE_ss
p_val_ss = (1 - pt(q = q_val_ss, df = df_val_ss)) * 2

konf_t_ss = qt(p = (1+0.95)/2, df = df_val_ss)
konf_ss = c(dif_ss - konf_t_ss * SE_ss, dif_ss - konf_t_ss * SE_ss)

dif_sh = schoolMean - hospitalMean;
df_val_sh = min(length(schoolCorrect), length(hospitalCorrect)) - 1
SE_sh = sqrt((sd(schoolCorrect)**2)/length(schoolCorrect) + (sd(hospitalCorrect)**2)/length(hospitalCorrect))
q_val_sh = (dif_sh - 0) / SE_sh
p_val_sh = (1 - pt(q = q_val_sh, df = df_val_sh)) * 2

konf_t_sh = qt(p = (1+0.95)/2, df = df_val_sh)
konf_sh = c(dif_sh - konf_t_sh * SE_sh, dif_sh + konf_t_sh * SE_sh)

