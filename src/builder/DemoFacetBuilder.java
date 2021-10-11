package builder;

/**
 * Kullanici: ozgen
 * Tarih: 10.10.2021
 * Saat: 15:33
 * Aciklama :
 */

class Worker {

    public String streetAddress, postCode, city;

    public String companyName, position;

    public int annualIncome;

    @Override
    public String toString() {
        return "Worker{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}

class WorkerBuilder {

    protected Worker worker = new Worker();

    public WorkerAddressBuilder lives() {
        return new WorkerAddressBuilder(worker);
    }

    public WorkerJobBuilder works() {
        return new WorkerJobBuilder(worker);
    }

    public Worker build() {
        return worker;
    }
}

class WorkerAddressBuilder extends WorkerBuilder {

    public WorkerAddressBuilder(Worker worker) {
        this.worker = worker;
    }

    public WorkerAddressBuilder at(String streetAddress) {
        worker.streetAddress = streetAddress;
        return this;
    }

    public WorkerAddressBuilder withPostCode(String postCode) {
        worker.postCode = postCode;
        return this;
    }

    public WorkerAddressBuilder in(String city) {
        worker.city = city;
        return this;
    }
}

class WorkerJobBuilder extends WorkerBuilder {
    public WorkerJobBuilder(Worker worker) {
        this.worker = worker;
    }

    public WorkerJobBuilder at(String companyName) {
        worker.companyName = companyName;
        return this;
    }

    public WorkerJobBuilder asA(String position) {
        worker.position = position;
        return this;
    }

    public WorkerJobBuilder earning(int annualIncome) {
        worker.annualIncome = annualIncome;
        return this;
    }
}

public class DemoFacetBuilder {

    public static void main(String[] args) {
        WorkerBuilder wb = new WorkerBuilder();
        Worker worker = wb
                .lives()
                .at("1234 street")
                .in("Ankara")
                .withPostCode("06600")
                .works()
                .at("ABC Company")
                .asA("Developer")
                .earning(12345)
                .build();
        System.out.println(worker);
    }
}
