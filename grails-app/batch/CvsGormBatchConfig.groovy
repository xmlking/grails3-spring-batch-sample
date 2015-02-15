import springbatchsample.*

import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper
import org.springframework.batch.item.file.mapping.DefaultLineMapper
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer
import org.springframework.batch.item.file.FlatFileItemReader

def root = "data"
def t1 = new File(root, "TABLE_ONE.csv")
println "T1 path: ${t1.absolutePath}; exists: ${t1.exists()}"

beans {

    batch.job(id: 'cvsGormJob') {
        batch.step(id: 'logStart', next: 'tableOneStep') {
            batch.tasklet(ref: 'printStartMessage')
        }
        batch.step(id: 'tableOneStep', next:'logFinish'){
            batch.tasklet{
                batch.chunk(
                        reader: "tableOneItemReader"
                        ,writer: "modelItemWriter"
                        ,'commit-interval': 1
                )
            }
        }

        batch.step(id: 'logFinish') {
            batch.tasklet(ref: 'printEndMessage')
        }
    }

    printStartMessage(PrintStartMessageTasklet) { bean ->
        bean.autowire = "byName"
    }
    printEndMessage(PrintEndMessageTasklet) { bean ->
        bean.autowire = "byName"
    }

    tableOne(TableOne){ bean ->
        bean.scope = "prototype"
    }

    tableOneLineMapper(DefaultLineMapper){ bean ->
        lineTokenizer = {DelimitedLineTokenizer lineTokenizer -> names = "one,two,anInt" }
        fieldSetMapper = {BeanWrapperFieldSetMapper fieldSetMapper -> prototypeBeanName = "tableOne"}
    }

    tableOneItemReader(FlatFileItemReader){ bean ->
        linesToSkip = 1
        resource = "file:data/TABLE_ONE.csv"
        lineMapper = ref("tableOneLineMapper")
    }

    modelItemWriter(ModelItemWriter)
}